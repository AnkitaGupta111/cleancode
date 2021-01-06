package com.zemoso.zinteract.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")

public class User {

	@Id
	private String id;

	@Field(name="username")
	private String userName;

	private String password;

	private boolean active;

	private String roles;

}
