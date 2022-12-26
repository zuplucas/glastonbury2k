package br.com.zup.order.orchestrator.subscription;

import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.BooleanValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class BookingTaskConfiguration {

    private final BookingTaskHandler handler;

    public BookingTaskConfiguration(BookingTaskHandler handler) {
        this.handler = handler;
    }

    @Bean
    @ExternalTaskSubscription("bookingTopic")
    public ExternalTaskHandler bookingExternalTaskHandler() {
        return (externalTask, externalTaskService) -> {
            System.out.println("Running external task handleBook");
            // retrieve a variable from the Process Engine
            String orderVariable = externalTask.getVariable("ORDER");

            handler.handleBook(orderVariable);

            // create an object typed variable
            BooleanValue newValue = Variables.booleanValue(true);

            // complete the external task
            externalTaskService.complete(externalTask,
                    Collections.singletonMap("booked", newValue));

            System.out.println("The External Task " + externalTask.getId()
                    + " for process " + externalTask.getProcessInstanceId()
                    + " has been completed!");
        };
    }

}
