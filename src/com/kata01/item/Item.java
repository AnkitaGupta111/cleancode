package com.kata01.item;

import com.kata01.discount.DiscountStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item implements Comparable<Item> {

  private long id;
  private String name;
  private float price;
  private String description;
  private int discount;
  private DiscountStrategy discountStrategy;
  private int cartQuantity;


  public int compareTo(Item item) {
    return (int) (this.id - item.id);
  }
}
