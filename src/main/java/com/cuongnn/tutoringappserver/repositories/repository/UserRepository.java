package com.cuongnn.tutoringappserver.repositories.repository;

import com.cuongnn.tutoringappserver.entities.User;
import com.cuongnn.tutoringappserver.repositories.custom.CustomUserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
}
