package com.kata01.engine;

import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;
import java.util.HashMap;
import java.util.Map;

public interface RuleEngine {

  static Map<Long , ItemOffer> offerCache = new HashMap<>();

  void calculateItemPrice(Item item);
}
