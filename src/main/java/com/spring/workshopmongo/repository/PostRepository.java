package com.spring.workshopmongo.repository;

import com.spring.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {  // necessario dois dados para o MongoRepository
                                                                         // saber o tipo de dominio que irá referenciar
  @Query("{ 'title': { $regex: ?0, $options: 'i' } }")  // parametro na url
  List<Post> searchTitle(String text);
  List<Post> findByTitleContainingIgnoreCase(String text);   // ignorar maiuscula e minusculo

  @Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} }, " +
         "{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, " +
         "{ 'body': { $regex: ?0, $options: 'i' } }, " +
         "{ 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
  List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
