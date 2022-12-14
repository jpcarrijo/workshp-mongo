package com.spring.workshopmongo.resources;

import com.spring.workshopmongo.DTO.UserDTO;
import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

  @Autowired
  private UserService service;

  //********* Busca todos ******************
  @GetMapping         // pode ser também @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserDTO>> findAll() {
//    User maria = new User("1", "Maria Silva", "maria@gmail.com");
//    User alex = new User("2", "Alex Green", "alex@gmail.com");     ficaria assim sem a instanciação do UserSerice
    List<User> list = service.findAll();
    List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); // convertendo para lista
    return ResponseEntity.ok().body(listDTO);
  }

  // ********Busca por ID *******************
  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    User obj = service.findById(id);
    return ResponseEntity.ok().body(new UserDTO(obj));
  }

  //********* Inserir ***************
  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDTO userDto) {
    User obj = service.fromDTO(userDto);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // boa prática. retorna o id
    return ResponseEntity.created(uri).build();
  }

  // ********* Delete ***************
  @DeleteMapping(value = "/{id}") // necessário passar o valor
  public ResponseEntity<UserDTO> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();   // delete retorna o código 204. no ResponseEntity é o noContent().build()
  }

  // ******** Atualizar **************
  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@RequestBody UserDTO userDto, @PathVariable String id) {
    User obj = service.fromDTO(userDto);
    obj.setId(id);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();
  }

  // ********* Retorna Posts **************
  @GetMapping(value = "/{id}/posts")
  public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
    User post = service.findById(id);
    return ResponseEntity.ok().body(post.getPosts());
  }
}
