package com.spring.workshopmongo.service;

import com.spring.workshopmongo.DTO.UserDTO;
import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.repository.UserRepository;
import com.spring.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(String id) {
    Optional<User> user = repository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Object Not Found!"));
  }

  public User insert(User obj) {
    return repository.insert(obj);
  }

  public void delete(String id) {
    findById(id);
    repository.deleteById(id);
  }

  public User update(User obj) {
    User upUser = findById(obj.getId());
    updateData(upUser, obj);
    return repository.save(upUser);
  }

  private void updateData(User upUser, User obj) {
    upUser.setName(obj.getName());
    upUser.setEmail(obj.getEmail());
  }

  public User fromDTO(UserDTO objDTO) {
    return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
  }
}


