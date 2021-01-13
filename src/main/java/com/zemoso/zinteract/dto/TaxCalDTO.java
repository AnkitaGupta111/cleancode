package com.zemoso.zinteract.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Tax calculator dto, used as Data Transfer Object over the network.
 */
@Getter
@Setter
@ToString
public class TaxCalDTO {
    private String income;
    private String investment_80c;
    private String ruleId;
}
