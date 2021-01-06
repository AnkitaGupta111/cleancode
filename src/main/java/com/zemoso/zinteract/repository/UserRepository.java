package com.zemoso.zinteract.repository;

import com.zemoso.zinteract.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUserName(String userName);

}
