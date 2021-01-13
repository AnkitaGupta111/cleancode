package com.zemoso.zinteract.service;

import com.google.gson.Gson;
import com.zemoso.zinteract.TaxCalculator;
import com.zemoso.zinteract.dto.TaxCalDTO;
import com.zemoso.zinteract.exception.DataNotFoundException;
import com.zemoso.zinteract.model.Rule;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Tax Calculator Service,
 * To Calculate the tax, by communicating with TaxCalculator.execute.
 */
@Service
public class TaxCalService implements ITaxCalService {

    private final IRuleService ruleService;

    /**
     * Instantiates a new Rule Service.
     *
     * @param ruleService the rule service
     */
    public TaxCalService(IRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    public Double calculateTaxToBePaid(TaxCalDTO taxCalDTO) {
        Rule rule;
        try {
            rule = ruleService.getRule(taxCalDTO.getRuleId());
        } catch (DataNotFoundException e) {
            return null;
        }
        Map<String, String> values = new HashMap<>();
        values.put("income", taxCalDTO.getIncome());
        values.put("investment_80c", taxCalDTO.getInvestment_80c());
        return TaxCalculator.execute(values, new Gson().toJson(rule));
    }
}
