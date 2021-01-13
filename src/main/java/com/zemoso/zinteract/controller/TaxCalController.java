package com.zemoso.zinteract.controller;


import com.zemoso.zinteract.dto.TaxCalDTO;
import com.zemoso.zinteract.service.ITaxCalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Tax Calculator Controller, to calculate the tax.
 */
@RestController
public class TaxCalController {
    private ITaxCalService iTaxCalService;

    public TaxCalController(ITaxCalService iTaxCalService) {
        this.iTaxCalService = iTaxCalService;
    }

    /**
     *  To calculate the tax based on the rules, values defined as a @param taxCalculatorDTO and
     * @return The tax to be paid
     */
    @PostMapping("/calculate-tax")
    public ResponseEntity<String> calculateTax(@RequestBody TaxCalDTO taxCalculatorDTO) {
        Double taxToBePaid = iTaxCalService.calculateTaxToBePaid(taxCalculatorDTO);
        if (taxToBePaid == null)
            return ResponseEntity.ok("Please provide valid data " + taxCalculatorDTO);
        return ResponseEntity.ok("Tax to be Paid = " + taxToBePaid);
    }
}
