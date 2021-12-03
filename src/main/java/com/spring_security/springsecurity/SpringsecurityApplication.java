package com.spring_security.springsecurity;

import com.spring_security.springsecurity.entity.Product;
import com.spring_security.springsecurity.entity.Role;
import com.spring_security.springsecurity.entity.User;
import com.spring_security.springsecurity.repository.ProductRepository;
import com.spring_security.springsecurity.repository.RoleRepository;
import com.spring_security.springsecurity.service.ProductService;
import com.spring_security.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SpringsecurityApplication implements CommandLineRunner {

	@Autowired
	ProductRepository productRepository;

    @Autowired
    UserService userService;
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product1 = Product.builder()
								  .id(1)
								  .name("mouse")
								  .price(350)
								  .description("orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the in")
								  .addedby("emp1")
								  .addedbyLevel("EMPLOYEE")
								  .build();

		Product product2 = Product.builder()
								   .id(2)
								   .name("mi laptop")
								   .price(60000)
								   .description("orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the in")
								   .addedby("admin")
								   .addedbyLevel("ADMIN").build();

		Product product3 = Product.builder()
				.id(3)
				.name("tea tree")
				.price(440)
				.description("orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the in")
				.addedby("emp1")
				.addedbyLevel("EMPLOYEE").build();

		Product product4 = Product.builder()
				.id(4)
				.name("mango shek")
				.price(40)
				.description("orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the in")
				.addedby("emp1")
				.addedbyLevel("EMPLOYEE").build();

		Product product5 = Product.builder()
				.id(5)
				.name("lenova laptop")
				.price(43000)
				.description("orem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the in")
				.addedby("admin")
				.addedbyLevel("ADMIN").build();

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);
		productRepository.save(product5);

        // User Creation... by Default 3 user created for three roles [User],[EMPLOYEE] and [ADMIN]...

		Role role_admin = new Role("ROLE_ADMIN");
		Role role_emp = new Role("ROLE_EMPLOYEE");
		Role role_user = new Role("ROLE_USER");

		roleRepository.saveAll(Arrays.asList( role_admin,role_user,role_emp ));


        User user1 = User.builder()
                             .username("user1")
                             .password("user1")
							 .email("user1@gmail.com")
							 .age(21)
							 .role(role_user)
							 .isEnabled(true)
                             .build();

        User emp1 = User.builder()
                            .username("emp1")
                            .password("emp1")
							.email("emp1@gmail.com")
							.age(23)
                            .role(role_emp)
							.isEnabled(true)
                            .build();

		User emp2 = User.builder()
				.username("emp2")
				.password("emp2")
							.email("emp2@gmail.com")
							.age(23)
							.role(role_emp)
							.isEnabled(false)
							.build();
		User emp3 = User.builder()
				.username("emp3")
				.password("emp3")
				.email("emp3@gmail.com")
				.age(25)
				.role(role_emp)
				.isEnabled(true)
				.build();

        User admin = User.builder()
                             .username("admin")
                             .password("admin")
                             .role(role_admin)
							 .email("admin@gmail.com")
							 .isEnabled(true)
                             .build();

        userService.userCreation(user1);
        userService.userCreation(emp1);
		userService.userCreation(emp2);
		userService.userCreation(emp3);
        userService.userCreation(admin);


	}

}
