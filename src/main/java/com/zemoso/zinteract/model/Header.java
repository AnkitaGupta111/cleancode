package com.zemoso.zinteract.model;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Header {

  private List<Condition> conditions;

  private List<Script> scripts;

  private List<Action> actions;


}
