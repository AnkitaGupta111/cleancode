package com.zemoso.zinteract.controller;


import com.zemoso.zinteract.dto.TaxCalDTO;
import com.zemoso.zinteract.service.ITaxCalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {
    private ITaxCalService iTaxCalService;

    public TaxController(ITaxCalService iTaxCalService) {
        this.iTaxCalService = iTaxCalService;
    }

    @PostMapping("/calculate-tax")
    public ResponseEntity<String> calculateTax(@RequestBody TaxCalDTO taxCalculatorDTO) {
        System.out.println(taxCalculatorDTO);
        Double taxToBePaid = iTaxCalService.calculateTaxToBePaid(taxCalculatorDTO);
        if (taxToBePaid == null)
            return ResponseEntity.ok("Please provide valid data " + taxCalculatorDTO);
        return ResponseEntity.ok("Tax to be Paid = " + taxToBePaid);
    }
}
