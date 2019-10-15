package org.improving.tag;

import java.util.Random;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.improving.tag")
public class SpringContext {
    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
