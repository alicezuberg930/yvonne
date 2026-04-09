package server.rem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemApplication.class, args);
    }

}