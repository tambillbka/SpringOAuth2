package com.cuongnn.tutoringappserver.repositories.customImpl;

import com.cuongnn.tutoringappserver.entities.User;
import com.cuongnn.tutoringappserver.repositories.custom.CustomUserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    //_Private_Variable________________________________________________
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";

    //_Private_Template________________________________________________
    private final MongoTemplate mongoTemplate;

    public CustomUserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User findByUsername(String username) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and(USERNAME).is(username);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public boolean existByUsernameOrEmail(String username) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.orOperator(
                Criteria.where(USERNAME).is(username),
                Criteria.where(EMAIL).is(username));
        query.addCriteria(criteria);
        return mongoTemplate.exists(query, User.class);
    }
}
