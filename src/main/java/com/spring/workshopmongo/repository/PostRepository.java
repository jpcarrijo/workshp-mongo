package com.spring.workshopmongo.repository;

import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {  // necessario dois dados para o MongoRepository
                                                                         // saber o tipo de dominio que irá referenciar
  List<Post> findByTitleContaining(String text);

}
