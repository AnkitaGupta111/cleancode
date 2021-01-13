package com.zemoso.zinteract.repository;

import com.zemoso.zinteract.model.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Rule repository.
 */
@Repository
public interface RuleRepository extends MongoRepository<Rule, String> {
}
