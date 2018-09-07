package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by k on 2018/8/13.
 */
public interface UserRepository extends MongoRepository<User, Integer> {
}
