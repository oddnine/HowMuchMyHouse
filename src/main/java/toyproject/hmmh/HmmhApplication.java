package toyproject.hmmh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HmmhApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(HmmhApplication.class, args);
		System.out.println("AptController 부 참조하세요.");
	}

}
