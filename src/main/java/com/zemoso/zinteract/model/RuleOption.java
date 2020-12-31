package com.zemoso.zinteract.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RuleOption {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Condition> conditions;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Action> actions;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Script> scripts;



}
