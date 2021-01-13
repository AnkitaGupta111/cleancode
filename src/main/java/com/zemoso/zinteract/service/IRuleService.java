package com.zemoso.zinteract.service;

import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;

import java.util.List;

/**
 * The interface Rule service.
 */
public interface IRuleService {
  /**
   * Gets rule.
   *
   * @param ruleId the rule id
   * @return the rule
   * @throws DataNotFoundException the data not found exception
   */
  Rule getRule(String ruleId) throws DataNotFoundException;

  /**
   * Gets all rules.
   *
   * @return the all rules
   */
  List<Rule> getAllRules();

  /**
   * Save rule rule.
   *
   * @param rule the rule
   * @return the rule
   */
  Rule saveRule(Rule rule);

  /**
   * Update rule rule.
   *
   * @param rule the rule
   * @return the rule
   */
  Rule updateRule(Rule rule);

  /**
   * Delete rule.
   *
   * @param ruleId the rule id
   * @throws DataNotFoundException the data not found exception
   */
  void deleteRule(String ruleId) throws DataNotFoundException;
}
