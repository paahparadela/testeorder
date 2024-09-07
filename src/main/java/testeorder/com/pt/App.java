package testeorder.com.pt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "testeorder.com.pt.repository")
@EntityScan(basePackages = "testeorder.com.pt.model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
