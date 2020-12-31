package com.zemoso.zinteract.controller;

import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;
import com.zemoso.zinteract.service.IRuleService;
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

	private IRuleService ruleService;

	public RuleController(IRuleService ruleService) {
		this.ruleService = ruleService;
	}

	@PostMapping()
	public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
		return ResponseEntity.ok(ruleService.saveRule(rule));
	}

	@GetMapping("/{ruleId}")
	public ResponseEntity<Rule> getRule(@PathVariable String ruleId) throws DataNotFoundException {
		return ResponseEntity.ok(ruleService.getRule(ruleId));
	}

	@GetMapping
	public ResponseEntity<List<Rule>> getRules() {

		return ResponseEntity.ok(ruleService.getAllRules());
	}

	@PutMapping()
	public ResponseEntity<Rule> updateRule(@RequestBody Rule rule) {
		return ResponseEntity.ok(ruleService.updateRule(rule));
	}

	@DeleteMapping("/{ruleId}")
	public ResponseEntity<Rule> updateRule(@PathVariable String ruleId) {
		return ResponseEntity.ok(new Rule());
	}

}
