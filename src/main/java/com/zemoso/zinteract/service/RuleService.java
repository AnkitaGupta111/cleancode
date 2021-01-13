package com.zemoso.zinteract.service;

import com.mongodb.MongoSecurityException;
import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;
import com.zemoso.zinteract.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Rule service.
 */
@Service
public class RuleService implements IRuleService {

	private RuleRepository ruleRepository;

	/**
	 * Instantiates a new Rule service.
	 *
	 * @param ruleRepository the rule repository
	 */
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

	@Override
	public void deleteRule(String ruleId) throws DataNotFoundException {
		Rule rule = getRule(ruleId);
		ruleRepository.delete(rule);
	}
}
