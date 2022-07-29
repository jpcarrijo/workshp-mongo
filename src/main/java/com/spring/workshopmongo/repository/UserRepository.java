package com.spring.workshopmongo.repository;

import com.spring.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {  // necessario dois dados para o MongoRepository
                                                                             // saber o tipo de dominio que irá referenciar
}
