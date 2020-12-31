package com.zemoso.zinteract.service;

import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;
import java.util.List;

public interface IRuleService {
  Rule getRule(String ruleId) throws DataNotFoundException;

  List<Rule> getAllRules();

  Rule saveRule(Rule rule);

  Rule updateRule(Rule rule);
}
