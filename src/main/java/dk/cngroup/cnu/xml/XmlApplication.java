package dk.cngroup.cnu.xml;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmlApplication implements CommandLineRunner {

	private XmlService xmlService;

	public XmlApplication(XmlService xmlService){
		this.xmlService = xmlService;
	}

	public static void main(String[] args) {
		SpringApplication.run(XmlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
