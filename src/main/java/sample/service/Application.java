package sample.service;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class Application {


	@Value("${version}")
	private String version;
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private final AtomicLong counter = new AtomicLong();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api")
	public Message echo() {
		return echo("Hello service");
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/api/{message}")
	public Message echo(@PathVariable String message) {
		Message data = new Message(counter.incrementAndGet(), message, version);
		log.info(data.toString());
		return data;
	}
}