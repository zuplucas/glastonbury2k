# Glastonbury
Tech Leads Training

###1 - Implementar API Inventory
###2 - Validar no camunda se o booking ocorreu com sucesso. 
###2.1 - Configurar o Error Event na task do booking para atualizar o status da order
###3 - Implementar API Payment
###4 - Validar no camunda se o payment ocorreu com sucesso. 
###4.1 - Ocorrendo falha, fazer a saga de compensação
###4.1.1 - Desfazimento do booking no inventory
###4.1.2 - Atualizar o status da order
###4.2 - Sucesso - seguir para atualizar o status da order
