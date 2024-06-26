package com.cyrilsoft;

import com.cyrilsoft.customer.Customer;
import com.cyrilsoft.customer.CustomerRepository;
import com.cyrilsoft.customer.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


import java.util.Random;

@SpringBootApplication // All three below can be

//@ComponentScan(basePackages = "com.cyril")
//@EnableAutoConfiguration
//@Configuration

public class Main {
    public static void main(String[] args) {
//        SpringApplication.run(Main.class, args);

//  @RequestMapping(path = "api/v1/customer", method = RequestMethod.GET)

    ConfigurableApplicationContext applicationContext =
            SpringApplication.run(Main.class, args);
//    printBeans(applicationContext);

    }

  @Bean
    public Foo getFoo(){
       return new Foo("bar");
    }
    record Foo(String name){

    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {
            var faker = new Faker();
            Random random = new Random();
            Name name = faker.name();
            String firstName = name.firstName();

            String lastName = name.lastName();
            int age = random.nextInt(16,99);
            Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;
            Customer customer = new Customer(
                    firstName + " "+ lastName,
//                    faker.internet().safeEmailAddress(),
                    firstName.toLowerCase() + "." + lastName.toLowerCase() + "@cyrilsoft.com",
                    age,
                    gender);


//          List<Customer> customers = List.of(alex, jamila);
//          customerRepository.saveAll(customers);
            customerRepository.save(customer);

        };
    }

  /*  private static void printBeans(ConfigurableApplicationContext ctx){
        String[] beanDefinitionNames =
                ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }*/
//    }
}