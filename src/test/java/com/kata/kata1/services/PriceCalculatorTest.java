package com.kata.kata1.services;

import com.kata.kata1.discounts.Price;
import com.kata.kata1.discounts.impl.NoOfferPrice;
import com.kata.kata1.discounts.impl.OfferPrice;
import com.kata.kata1.pojos.Product;
import com.kata.kata1.services.impl.PriceCalculatorImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceCalculatorTest {
    Product aProduct = new Product('A', 50);
    Product bProduct = new Product('B', 30);
    Product cProduct = new Product('C', 20);
    Product dProduct = new Product('D', 15);

    Map<Product, Price> productPriceMap() {
        Map<Product, Price> productPriceMap = new HashMap<>();
        productPriceMap.put(aProduct, new OfferPrice(3, 130));
        productPriceMap.put(bProduct, new OfferPrice(2, 45));
        productPriceMap.put(cProduct, new NoOfferPrice());
        productPriceMap.put(dProduct, new NoOfferPrice());
        return productPriceMap;
    }

    @Test
    void testOfferPriceForMultipleProducts() {
        Map<Product, Price> productPriceMap = productPriceMap();
        PriceCalculator priceCalculator = new PriceCalculatorImpl(productPriceMap);
        List<Product> productList = new ArrayList<>();
        productList.add(aProduct);
        productList.add(aProduct);
        productList.add(aProduct);
        productList.add(bProduct);
        productList.add(bProduct);
        productList.add(cProduct);
        productList.add(bProduct);
        productList.add(aProduct);
        productList.add(dProduct);
        Map<Product, Integer> productIntegerMap = addProductListToProductMap(productList);
        // AAABBCBAD => 130+50+45+30+20
        assertEquals(290, priceCalculator.calculatePrice(productIntegerMap), 0);
    }

    @Test
    void testOfferPricesForProductA() {
        Map<Product, Price> productPriceMap = productPriceMap();
        PriceCalculator priceCalculator = new PriceCalculatorImpl(productPriceMap);
        List<Product> productList = new ArrayList<>();
        productList.add(aProduct);
        productList.add(aProduct);
        productList.add(aProduct);

        Map<Product, Integer> productIntegerMap = addProductListToProductMap(productList);
        // AAA => Offer price of 130. Actual price is 150.
        double priceOfThreeA = priceCalculator.calculatePrice(productIntegerMap);
        assertEquals(130, priceOfThreeA, 0);
    }

    @Test
    void testPriceForOneProductOfEachType() {
        Map<Product, Price> productPriceMap = productPriceMap();
        PriceCalculator priceCalculator = new PriceCalculatorImpl(productPriceMap);

        List<Product> productList = new ArrayList<>();
        productList.add(aProduct);
        productList.add(bProduct);
        Map<Product, Integer> productIntegerMap = addProductListToProductMap(productList);

        // A => 50, B => 30
        assertEquals(80, priceCalculator.calculatePrice(productIntegerMap), 0);

        productList.add(cProduct);
        productList.add(dProduct);
        productIntegerMap = addProductListToProductMap(productList);

        // A => 50, B => 30, C => 20, D=>15
        assertEquals(115, priceCalculator.calculatePrice(productIntegerMap), 0);
    }

    private Map<Product, Integer> addProductListToProductMap(List<Product> productList) {
        Map<Product, Integer> productIntegerMap = new HashMap<>();
        productList.forEach(product -> {
            int productSize = productIntegerMap.containsKey(product) ? productIntegerMap.get(product) + 1 : 1;
            productIntegerMap.put(product, productSize);
        });
        return productIntegerMap;
    }
}
