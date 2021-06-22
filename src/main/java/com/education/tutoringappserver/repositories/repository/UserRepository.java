package com.education.tutoringappserver.repositories.repository;

import com.education.tutoringappserver.entities.User;
import com.education.tutoringappserver.repositories.custom.CustomUserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
}
