package com.zemoso.zinteract.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rule")
@ToString
public class Rule {

	@Id
	private String id;

	private String name;

	private String description;

	@Field(name="artifact_id")
	@JsonProperty("artifact_id")
	private String artifact_id;

	private List<Option> options;

	@Field(name="headers")
	@JsonProperty("headers")
	private Header headers;

	@Field(name="rows")
	@JsonProperty("rows")
	private List<Rows> rows;

}
