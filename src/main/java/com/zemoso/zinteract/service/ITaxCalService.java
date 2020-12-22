package com.zemoso.zinteract.service;

import com.zemoso.zinteract.dto.TaxCalDTO;

public interface ITaxCalService {
    Double calculateTaxToBePaid(TaxCalDTO ruleId);

}
