package com.shop.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import com.shop.model.*;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;

@Component
public class Seeder implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public Seeder(RoleRepository roleRepository, UserRepository userRepository, CategoryRepository categoryRepository,
            ProductRepository productRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        int max = 10;
        Faker faker = new Faker(new Locale("en-US"));
        HashMap<Integer, Role> mapRoles = new HashMap<>();
        HashMap<Integer, User> mapUsers = new HashMap<>();
        HashMap<Integer, Category> mapCategories = new HashMap<>();
        HashMap<Integer, Product> mapProducts = new HashMap<>();

        Role role1 = Role.builder().name("admin").build();
        Role role2 = Role.builder().name("user").build();

        mapRoles.put(1, role1);
        mapRoles.put(2, role2);

        for (int i = 0; i < max; i++) {
            User user = User.builder().email(faker.internet().emailAddress()).userName(faker.name().username())
                    .password(passwordEncoder.encode("password")).role(i == 0 ? role1 : role2).build();

            Category category = Category.builder().name(faker.lorem().word()).description(faker.lorem().sentence())
                    .build();

            mapUsers.put(i, user);
            mapCategories.put(i, category);
        }

        Random random = new Random();
        for (int i = 0; i < max; i++) {
            Product product = Product.builder().designation(faker.lorem().word()).price(faker.number().randomDouble(2, 10, 400)).quantity(faker.number().numberBetween(20, 100))
                    .category(mapCategories.get(random.nextInt(mapCategories.size()))).build();
            mapProducts.put(i, product);
        }

        mapRoles.forEach((i, role) -> roleRepository.save(role));
        mapUsers.forEach((i, user) -> userRepository.save(user));
        mapCategories.forEach((i, category) -> categoryRepository.save(category));
        mapProducts.forEach((i, product) -> productRepository.save(product));

    }

}
