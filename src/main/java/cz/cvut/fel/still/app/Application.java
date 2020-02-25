package cz.cvut.fel.still.app;

import co.elastic.apm.attach.ElasticApmAttacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.time.Instant;

@SpringBootApplication
@Slf4j
@EnableConfigurationProperties(MailConfiguration.class)
public class Application implements CommandLineRunner {

	@Autowired
	private BuildInfo service;

	@Autowired
	private MailConfiguration mailConfiguration;

	@Value("${whoami}")
	private String whoami;

	@Autowired
	private Environment env;

	@Autowired
	private PrintUsersTask printUsersTask;

	public static void main(String[] args) {
        ElasticApmAttacher.attach();

	    SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.error("Something else is wrong here");
		log.trace("Logging at TRACE level");
		log.debug("Logging at DEBUG level");
		log.info("Logging at INFO level");
		log.warn("Logging at WARN level");
		log.error("Logging at ERROR level");
		log.debug("Today is {}", java.util.Date.from(Instant.now()));

		log.info("Application {} ({}) has started ...", service.getApplicationName(), service.getApplicationVersion());
		log.info(mailConfiguration.toString());

		log.info("whoami = '{}'", whoami);
		log.info("Env.JAVA_HOME = {}", env.getProperty("JAVA_HOME"));
		log.info("Env.CUSTOM = {}", env.getProperty("CUSTOM"));

        String rawString = "This is my string " + System.lineSeparator() + " which I want to be" + System.lineSeparator() + " on multiple lines.\r\nIt can contain backslash \\\r\nOr even quotes \"";
		log.info("multiline = {}", rawString);

		String s = "Here the function getAlphaNumericString(n) generates a random number of length a string. This number is an index of a Character and this Character is appended in temporary local variable sb. In the end sb is returned.";
		log.info("toolong = {}", s + s + s + s);

		printUsersTask.execute();
	}
}
