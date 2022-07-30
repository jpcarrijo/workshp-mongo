package com.spring.workshopmongo.resources;

import com.spring.workshopmongo.DTO.UserDTO;
import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.service.PostService;
import com.spring.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResources {

  @Autowired
  private PostService postService;

  // ********Busca por ID *******************
  @GetMapping(value = "/{id}")
  public ResponseEntity<Post> findById(@PathVariable String id) {
    Post obj = postService.findById(id);
    return ResponseEntity.ok().body(obj);
  }
}
