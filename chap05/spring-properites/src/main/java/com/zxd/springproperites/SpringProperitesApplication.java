package com.zxd.springproperites;

import com.zxd.springproperites.data.IngredientRepository;
import com.zxd.springproperites.model.Ingredient;
import com.zxd.springproperites.model.Ingredient.Type;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringProperitesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProperitesApplication.class, args);
    }
}
