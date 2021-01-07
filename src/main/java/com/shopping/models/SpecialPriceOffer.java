package com.shopping.models;

/**
 * This class has the properties of an Offer
 */
public class SpecialPriceOffer {
    private String offerName;
    private int offerQuantity;
    private float specialPrice;

    public SpecialPriceOffer( final String offerName, final int offerQuantity, float specialPrice) {
        this.offerName = offerName;
        this.offerQuantity = offerQuantity;
        this.specialPrice = specialPrice;
    }

    public String getOfferName() {
        return offerName;
    }

    public int getOfferQuantity() {
        return offerQuantity;
    }

    public float getSpecialPrice() {
        return specialPrice;
    }
}
