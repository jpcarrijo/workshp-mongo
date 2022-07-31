package com.spring.workshopmongo.service;

import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.repository.PostRepository;
import com.spring.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    return postRepository.searchTitle(text);
  }

  public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
    maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
    return postRepository.fullSearch(text, minDate, maxDate);
  }
}


