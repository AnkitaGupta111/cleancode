package com.kata01.engine;

import com.google.gson.Gson;
import com.kata01.discount.DiscountStrategy;
import com.kata01.discount.DiscountStrategyFactory;
import com.kata01.discount.type.DiscountType;
import com.kata01.dto.ItemOffer;
import com.kata01.model.Item;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;


public class MyStoreRuleEngine implements RuleEngine {


  @Override
  public void calculateItemPrice(Item item) {
    ItemOffer itemOffer = getOfferOnItem(item.getId())
        .orElse(ItemOffer.builder().discountType(DiscountType.NO_OFFER).build());
    DiscountStrategyFactory factory = new DiscountStrategyFactory();
    DiscountStrategy strategy = factory.getDiscountStrategy(itemOffer);
    strategy.setPriceAfterDiscount(item, itemOffer);
  }


  private Optional<ItemOffer> getOfferOnItem(long id) {
    if (offerCache.get(id) != null) {
      return Optional.of(offerCache.get(id));
    } else {
      System.out.println("backend cal...");
      Optional<ItemOffer> response = Optional.empty();
      try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:3000/offers/" + id))
            .build();
        HttpResponse<String> resp;
        resp = client.send(request,
            HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        ItemOffer itemOffer
            = gson.fromJson(resp.body(),
            ItemOffer.class);
        response = Optional.ofNullable(itemOffer);
        offerCache.put(id,itemOffer);
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }

      return response;
    }
  }
}
