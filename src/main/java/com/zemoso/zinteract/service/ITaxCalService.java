package com.zemoso.zinteract.service;

import com.zemoso.zinteract.dto.TaxCalDTO;

/**
 * The interface Tax cal service.
 */
public interface ITaxCalService {
    /**
     * Calculate tax to be paid.
     *
     * @param ruleId the rule id
     * @return the double
     */
    Double calculateTaxToBePaid(TaxCalDTO ruleId);

}
