package net.kruglov.servicebook;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.server.WebGraphQlInterceptor;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class ServicebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicebookApplication.class, args);
	}

	@Bean
	public WebGraphQlInterceptor interceptor() {
		return (webInput, interceptorChain) -> {
			return Mono.delay(Duration.ofMillis(10)).flatMap(aLong -> interceptorChain.next(webInput));
		};
	}

} 



