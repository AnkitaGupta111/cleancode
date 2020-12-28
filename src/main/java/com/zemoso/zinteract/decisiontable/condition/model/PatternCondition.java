package com.zemoso.zinteract.decisiontable.condition.model;

import com.zemoso.zinteract.decisiontable.condition.DecisionTableCondition;
import java.util.regex.Matcher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PatternCondition {

  private Matcher matcher;
  private DecisionTableCondition condition;
}
