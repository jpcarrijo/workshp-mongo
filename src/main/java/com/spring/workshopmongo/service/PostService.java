package com.spring.workshopmongo.service;

import com.spring.workshopmongo.DTO.UserDTO;
import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.repository.PostRepository;
import com.spring.workshopmongo.repository.UserRepository;
import com.spring.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Post findById(String id) {
    Optional<Post> user = postRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
  }

  public List<Post> findByTitle(String text) {
    return postRepository.findByTitleContaining(text);
  }
}


