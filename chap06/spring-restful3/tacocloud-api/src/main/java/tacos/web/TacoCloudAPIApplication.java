package tacos.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "taco.data"
})
public class TacoCloudAPIApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(TacoCloudAPIApplication.class, args);
    }
}
