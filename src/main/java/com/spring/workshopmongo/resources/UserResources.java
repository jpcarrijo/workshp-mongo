package com.spring.workshopmongo.resources;

import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

  @Autowired
  private UserService service;

  @GetMapping         // pode ser também @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<User>> findAll() {
//    User maria = new User("1", "Maria Silva", "maria@gmail.com");
//    User alex = new User("2", "Alex Green", "alex@gmail.com");     ficaria assim sem a instanciação do UserSerice
    List<User> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
}
