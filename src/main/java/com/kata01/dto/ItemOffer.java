package com.kata01.dto;

import com.kata01.discount.type.DiscountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemOffer {

  private long id;
  private String name;
  private DiscountType discountType;
  private float discountPercentage;
  private float discountAmount;
  private int quantity;

  @Override
  public String toString() {
    return id + ":" + name + ":" + discountType;
  }
}
