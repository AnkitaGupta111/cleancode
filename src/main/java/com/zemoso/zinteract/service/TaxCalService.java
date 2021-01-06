package com.zemoso.zinteract.service;

import com.google.gson.Gson;
import com.zemoso.zinteract.TaxCalculator;
import com.zemoso.zinteract.dto.TaxCalDTO;
import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaxCalService implements ITaxCalService {

    private IRuleService ruleService;

    public TaxCalService(IRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    public Double calculateTaxToBePaid(TaxCalDTO taxCalDTO) {
        Rule rule = null;
        try {
            rule = ruleService.getRule(taxCalDTO.getRuleId());
        } catch (DataNotFoundException e) {
            return null;
        }
        Map<String, String> values = new HashMap<>();
        values.put("income", taxCalDTO.getIncome());
        values.put("investment_80c", taxCalDTO.getInvestment_80c());
        System.out.println(new Gson().toJson(rule));
        System.out.println();
        System.out.println(values);
        System.out.println();

//        return TaxCalculator.execute(values, new Gson().toJson(rule));
        String json = TaxCalculator.getRulesJson();
        System.out.println(json);
        return TaxCalculator.execute(values, json);
    }
}
