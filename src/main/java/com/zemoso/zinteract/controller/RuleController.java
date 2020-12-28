package com.zemoso.zinteract.controller;

import com.zemoso.zinteract.model.Rule;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rules")
public class RuleController {

	@PostMapping("/rule")
	public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
		return ResponseEntity.ok(rule);
	}

	@GetMapping("/rule/{ruleId}")
	public ResponseEntity<Rule> getRule(@PathVariable String ruleId) {
		return ResponseEntity.ok(new Rule());
	}

	@GetMapping("/rule")
	public ResponseEntity<List<Rule>> getRules() {
		return ResponseEntity.ok(Collections.emptyList());
	}

	@PutMapping("/rule")
	public ResponseEntity<Rule> updateRule(@RequestBody Rule rule) {
		return ResponseEntity.ok(rule);
	}

	@DeleteMapping("/rule/{ruleId}")
	public ResponseEntity<Rule> updateRule(@PathVariable String ruleId) {
		return ResponseEntity.ok(new Rule());
	}

}
