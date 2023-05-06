package com.distribuidos.secretariasalud.expedientes;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.distribuidos.secretariasalud.expedientes.rabbitConfig.Receiver;

@SpringBootApplication
public class ExpedientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpedientesApplication.class, args);
	}

}
