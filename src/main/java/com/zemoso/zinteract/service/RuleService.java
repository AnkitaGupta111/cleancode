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

  /**
   * Get rule returns the rule based on Id
   *
   * @param ruleId unique identifier for rule
   * @return rule
   * @throws DataNotFoundException if no data found
   */
  @Override
  public Rule getRule(String ruleId) throws DataNotFoundException {
    try {
      return ruleRepository.findById(ruleId)
          .orElseThrow(() -> new DataNotFoundException("No record with rule Id:" + ruleId));
    } catch (MongoSecurityException e) {
      throw new DataNotFoundException("No record with rule Id:" + ruleId, e);
    }
  }

  /**
   * Returns all the rules in the system
   *
   * @return all the rules
   */
  @Override
  public List<Rule> getAllRules() {
    return ruleRepository.findAll();
  }

  /**
   * Save the new rule
   *
   * @param rule definition
   * @return saved rule
   */
  @Override
  public Rule saveRule(Rule rule) {
    return ruleRepository.save(rule);
  }

  /**
   * Update or patch the exisiting rule
   *
   * @param rule definition
   * @return updated rule
   */
  @Override
  public Rule updateRule(Rule rule) {
    return ruleRepository.save(rule);
  }

	@Override
	public String deleteRule(String ruleId) {
		ruleRepository.deleteById(ruleId);
		return ruleId;
	}
}
