package com.logztechstuff.camel.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelRoutes {

	@Bean
	public RouteBuilder firstRoute() {
		
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("{{start.queue}}")
					.routeId("first-route")
					.log("${body}")
					.choice()
					.when().jsonpath("$.[?(@.contentAccepted == 1)]")
						.process(new Processor() {
							@Override
							public void process(Exchange arg0) throws Exception {
								System.out.println(String.valueOf(arg0.getIn().getBody()));
							}
						})
					.when().jsonpath("$.[?(@.contentAccepted == 2)]")
						.process(new Processor() {
							@Override
							public void process(Exchange arg0) throws Exception {
								System.out.println("When case 2");
								
							}
						})
					.otherwise()
						.process(new Processor() {
							@Override
							public void process(Exchange arg0) throws Exception {
								System.out.println("Otherwise Processor");
								
							}
						})
					;
			}
		};
	}
}
