package com.distribuidos.secretariasalud.expedientes.rabbitConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {
    
	static final String topicExchangeName = "expedientes";

	//static final String queueName = "solicitudes";
  
	@Bean
	Queue solicitudesQueue() {
	  return new Queue("solicitudes", false);
	}

    @Bean
	Queue aceptadosQueue() {
	  return new Queue("aceptados", false);
	}
  
	@Bean
	TopicExchange exchange() {
	  return new TopicExchange(topicExchangeName);
	}
  
	@Bean
	Binding aceptadosBinding(Queue aceptadosQueue, TopicExchange exchange) {
	  return BindingBuilder.bind(aceptadosQueue).to(exchange).with("aceptados.*");
	}

    @Bean
	Binding solicitudesBinding(Queue solicitudesQueue, TopicExchange exchange) {
	  return BindingBuilder.bind(solicitudesQueue).to(exchange).with("solicitudes.*");
	}
 
}
