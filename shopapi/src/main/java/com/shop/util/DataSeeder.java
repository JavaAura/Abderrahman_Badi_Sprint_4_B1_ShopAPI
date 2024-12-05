package com.shop.util;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import com.shop.model.*;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DataSeeder {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public void seedDatabase(int max) {
        Faker faker = new Faker(new Locale("en-US"));

        Role role1 = Role.builder().name("ROLE_ADMIN").build();
        Role role2 = Role.builder().name("ROLE_USER").build();
        roleRepository.saveAll(Arrays.asList(role1, role2));



        Instant start = Instant.now();
        List<Category> categories = IntStream.range(0, max).mapToObj(i -> Category.builder()
                .name(faker.lorem().word())
                .description(faker.lorem().sentence())
                .build()).collect(Collectors.toList());
        categoryRepository.saveAll(categories);

        CompletableFuture<Void> usersFuture = CompletableFuture.runAsync(() -> {
            log.info("Seeding users started...");

			Role adminRole = roleRepository.findByName("ROLE_ADMIN")
					.orElseThrow(() -> new RuntimeException("Admin role not found"));
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseThrow(() -> new RuntimeException("User role not found"));

            List<User> users = IntStream.range(0, max).parallel() // Parallel stream to reduce time taken in hashing the passwords
                    .mapToObj(i -> User.builder()
                            .email(faker.internet().emailAddress())
                            .username(faker.name().username())
                            .password(passwordEncoder.encode("password")) // Expensive operation
                            .role(i == 0 ? adminRole : userRole) // First user is admin
                            .build())
                    .collect(Collectors.toList());
            userRepository.saveAll(users);

            log.info("Seeding users completed");
        });

        CompletableFuture<Void> productsFuture = CompletableFuture.runAsync(() -> {
            log.info("Seeding products started...");
            Random random = new Random();
            List<Product> products = IntStream.range(0, max)
                    .mapToObj(i -> Product.builder()
                            .designation(faker.lorem().word())
                            .price(faker.number().randomDouble(2, 10, 400))
                            .quantity(faker.number().numberBetween(20, 100))
                            .category(categories.get(random.nextInt(categories.size())))
                            .build())
                    .collect(Collectors.toList());
            productRepository.saveAll(products);

            log.info("Seeding products completed");
        });

        CompletableFuture.allOf(usersFuture, productsFuture).join(); // Ensures both tasks are completed 

        long timeTaken = (Instant.now().toEpochMilli() - start.toEpochMilli()) / 1000;

        log.info("Seeding complete : time taken " + timeTaken + " s");

    }

}
