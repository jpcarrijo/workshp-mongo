package com.spring.workshopmongo.config;

import com.spring.workshopmongo.DTO.AuthorDTO;
import com.spring.workshopmongo.DTO.CommentDTO;
import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.repository.PostRepository;
import com.spring.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration  // Spring enteder que é uma configuração
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @Override
  public void run(String... args) throws Exception {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

    userRepository.deleteAll();

    postRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));  // Arrays.asList para inserir mais de um

    Post post1 = new Post(null, sdf.parse("21/03/2022"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
    Post post2 = new Post(null, sdf.parse("23/03/2022"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().addAll(Arrays.asList(post1, post2));

    userRepository.save(maria);

    CommentDTO comments1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2022"), new AuthorDTO(alex));
    CommentDTO comments2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2022"), new AuthorDTO(bob));
    CommentDTO comments3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2022"), new AuthorDTO(alex));

    post1.getComments().addAll(Arrays.asList(comments1, comments2));
    post2.getComments().add(comments3);

    postRepository.saveAll(Arrays.asList(post1, post2));

  }
}
