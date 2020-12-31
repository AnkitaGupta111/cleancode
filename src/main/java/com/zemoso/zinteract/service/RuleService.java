package com.zemoso.zinteract.service;

import com.mongodb.MongoSecurityException;
import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;
import com.zemoso.zinteract.repository.RuleRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RuleService implements IRuleService {

	private RuleRepository ruleRepository;

	public RuleService(RuleRepository ruleRepository) {
		this.ruleRepository = ruleRepository;
	}

	@Override
	public Rule getRule(String ruleId) throws DataNotFoundException {
		try {
			return ruleRepository.findById(ruleId)
					.orElseThrow(() -> new DataNotFoundException("No record with rule Id:" + ruleId));
		}
		catch (MongoSecurityException e) {
			throw new DataNotFoundException("No record with rule Id:" + ruleId, e);
		}
	}

	@Override
	public List<Rule> getAllRules() {
		return ruleRepository.findAll();
	}

  @Override
  public Rule saveRule(Rule rule) {
    return ruleRepository.save(rule);
  }

	@Override
	public Rule updateRule(Rule rule) {
		return ruleRepository.save(rule);
	}
}
