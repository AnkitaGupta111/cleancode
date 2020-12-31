package com.zemoso.zinteract.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rule")
public class Rule {

	@Id
	private String id;

	private String name;

	private String description;

	@Field(name="artifact_id")
	@JsonProperty("artifact_id")
	private String artifactId;

	private List<Option> options;

	@Field(name="headers")
	@JsonProperty("headers")
	private Header header;

	@Field(name="rows")
	@JsonProperty("rows")
	private List<RuleOption> ruleOptions;

}
