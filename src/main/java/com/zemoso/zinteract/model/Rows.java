package com.zemoso.zinteract.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Rows {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Condition> conditions;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Action> actions;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<Script> scripts;



}
