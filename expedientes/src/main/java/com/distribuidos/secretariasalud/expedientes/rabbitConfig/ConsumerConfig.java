package com.distribuidos.secretariasalud.expedientes.rabbitConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.distribuidos.secretariasalud.expedientes.modelos.Permiso;

@Configuration
public class ConsumerConfig {
     
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
		MessageListenerAdapter listenerAdapter) {
	  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	  container.setConnectionFactory(connectionFactory);
	  container.setMessageListener(listenerAdapter);
	  return container;
	}
  
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
	  return new MessageListenerAdapter(receiver, "receiveMessage");
	}

    @RabbitListener(queues = "aceptados")
    public void receiveMessage(Permiso permiso) {
        System.out.println("Received <" + permiso.toString() + ">");
    }
}
