package com.zemoso.zinteract.controller;

import com.zemoso.zinteract.dto.RuleDTO;
import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;
import com.zemoso.zinteract.service.IRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class RuleController {

    private IRuleService ruleService;

    public RuleController(IRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping()
    public ResponseEntity<Rule> createRule(@RequestBody RuleDTO rule) {
        Rule modelRule = new Rule();
        BeanUtils.copyProperties(rule, modelRule);
        return ResponseEntity.ok(ruleService.saveRule(modelRule));
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
    public ResponseEntity deleteRule(@PathVariable String ruleId) {
        try {
            ruleService.deleteRule(ruleId);
        } catch (DataNotFoundException e) {
            ResponseEntity.badRequest();
        }
        return ResponseEntity.ok("Deleted Rule with Rule-Id = "+ruleId);
    }

}
