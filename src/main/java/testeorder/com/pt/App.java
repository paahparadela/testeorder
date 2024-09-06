package testeorder.com.pt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "testeorder.com.pt.repository")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
