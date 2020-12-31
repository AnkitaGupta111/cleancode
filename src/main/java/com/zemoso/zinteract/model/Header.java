package com.zemoso.zinteract.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Header {

  private List<Condition> conditions;

  private List<Script> scripts;

  private List<Action> actions;


}
