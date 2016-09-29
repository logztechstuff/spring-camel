package com.logztechstuff;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class FirstProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String message = String.valueOf(exchange.getIn().getBody());
		System.out.println(message);
	}
}
