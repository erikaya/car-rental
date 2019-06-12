package com.example.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBinding(Sink.class)
public class CarRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentalApplication.class, args);
	}
}

@Component
class FraudListener {

	Fraud fraud;

	@StreamListener(Sink.INPUT)
	void listen(Fraud fraud){
		System.out.println("\n\n\n A FRAUD IS COMING! " + fraud.toString());
		this.fraud = fraud;
	}
}

class Fraud {
	public String name;

	public Fraud() {
	}

	public Fraud(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Fraud{" +
				"name='" + name + '\'' +
				'}';
	}
}
