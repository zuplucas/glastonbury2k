# Glastonbury
Tech Leads Training

# Como subir a aplicação:

1- Inicie o docker-compose para subir o camunda e o banco postgres

2- Suba todos os módulos do projeto (não há dependência de ordem)
- Order
- Order Orchestrator
- Inventory
- Payment

3- Execute o seguinte comando curl para iniciar o processo:

```sh 
curl -X POST --header "Content-Type: application/json" http://localhost:8080/orders -d "{\
    \"customerId\": \"e081ad0f-f83e-4f81-b2d7-23655ac04b8a\", \
    \"amount\": 10.5, \
    \"items\": [ \
        { \
            \"id\": \"3852cb18-9c19-4326-ac77-a1a0264bd98c\",
            \"name\": \"day-one\",
            \"amount\" : 200.00,
            \"quantity\" : 1
        } \
    ] \
}"
```

Este comando gerará uma ordem de pedido e iniciará o workflow.
Porém, o workflow ficará parado, pois, a task que solicita o pagamento não está implementada.

4- Acesse o dashboard do workflow (http://localhost:8084) que subiu via docker-compose \
User: demo \
Password: demo

No dashboard, acesse os processos em execução e haverá um processo parado com erro no fluxo, informando o erro na tarefa.


# Qual o objetivo do treinamento?

Neste treinamento falamos sobre **SAGA pattern** utilizando orquestração.
No `SAGA` precisamos considerar a comunicação entre os serviços, 
porém precisamos tomar cuidado com os fluxos alternativos de erro.

O esqueleto do projeto está funcional, porém incompleto com relação ao fluxo. 
Sua missão é completar o fluxo, considerando fluxo de sucesso e fluxos alternativos. 
E toda tomada de decisão sobre qual o próximo passo a ser seguido deve estar no orquestrador. 

Abaixo segue um resumo de como o sistema deveria se comportar. \
Sinta-se a vontade em colocar mais passos, melhorar os feedbacks de serviços e comunicação. \
Note que usando o *camunda* como orquestrador, temos um processo visual para o cliente. Logo, seria uma boa prática, 
toda atividade de negócio estar indicada no fluxo, de tal forma que o usuário consiga identificar qual o 'step' ofensor,
qual o ‘step’ onde os clientes estão esperando mais tempo por respostas, etc.

Esta versão consta com um banco de dados postgres para o camunda. Desta forma, fica um armazenamento persistente 
(ao contrário do H2). Assim, caso necessite alterar o fluxo do camunda (utilizando o *camunda modeler*),
agora precisamos apenas salvar o bpmn na pasta (./docker-files/bpmns) e reiniciar o container do camunda.
Desde que o volume do docker do postgres não seja apagado, os fluxos ainda existirão e até mesmo os processos
antigos estarão no mesmo passo de quando o container do camunda foi reiniciado. Ou seja, podemos iniciar um fluxo de workflow,
verificar o problema, ajustar o processo, ou criar uma task de listener e reiniciar o camunda que os fluxos com problema
irão seguir.

## 1. Reservar o ticket
Em nosso fluxo, iniciamos com a "reserva dos ingressos", que poderá retornar que existe em estoque ou não. \
A não existencia em estoque agora já poderia finalizar o fluxo e acionar a api de Order para alterar o status dela para REJEITADO.

## 2. Solicitar pagamento
Após a confirmação da reserva dos ingressos, precisamos realizar o pagamento.\
Este pagamento é feito de forma assíncrona. Ou seja, acionamos a API de pagamento, pela "paymentTask" que irá indicar
que a requisição foi recebida e será processada posteriormente. \
Para isso a API de pagamento poderá retornar um ID de solicitação ou podemos amarrar com o ID da ordem.
Este ponto apenas geraria erro se houvesse algum problema na API de pagamentos (ou outro problema técnico), 
neste caso, podemos seguir com o fluxo de retentativa de solicitação.

A tarefa de pagamento deverá ser implementada semelhante ao booking. Haverá um tópico do camunda
que poderá ser ouvido *sendPaymentTopic*.

## 3. Confirmação ou Rejeição do pagamento
Após o processamento do pagamento, este poderá ser rejeitado ou aprovado.
E, então o sistema de pagamentos deverá enviar um callback para o orquestrador indicando qual a situação do pagamento.\
Para isso, existem 2 alternativas básicas de integração: \
a. Implementar um serviço no orquestrador que receberá o callback\
b. Realizar a comunicação do callback por messageria no kafka.

Iremos seguir com a primeira abordagem (já que esta versão seria sem o uso de kafka). 
O sistema de pagamentos deverá realizar uma requisição para o orquestrator informando a ordem e a situação do pagamento.

Há um controller no orquestrador que receberá essa requisição e irá repassar ao workflow o callback enviado.

### 3.1. Pagamento confirmado
Caso o pagamento seja confirmado, o fluxo da orquestração deverá seguir para o passo de finalização da ordem.

### 3.2. Pagamento não efetivado
Caso o pagamento não seja efetivado, deveremos seguir com o fluxo de compensação, pois necessitaremos remover a reserva
do ingresso realizado no *PASSO 1* e depois indicar ao sistema de ordem que o pedido ficou com o estado de PAGAMENTO NÃO EFETIVADO.

## 4. Finalização da Ordem
A ordem deverá ser finalizada no orquestrador conforme o estado do pagamento.
A finalização também precisa ser implementada através do tópico *finishOrderTopic* do camunda.
