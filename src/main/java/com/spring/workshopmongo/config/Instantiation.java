package com.spring.workshopmongo.config;

import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration  // Spring enteder que é uma configuração
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {

    userRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));  // Arrays.asList para inserir mais de um

  }
}
