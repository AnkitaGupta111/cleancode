import com.shopping.ShoppingBillingSystem;
import com.shopping.models.Product;
import com.shopping.offer.SpecialPriceOffer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ShoppingBillingSystemTest {
    @Test
    public void testOnTwoDifferntItems() {
        ShoppingBillingSystem shoppingBillingSystem = new ShoppingBillingSystem();
        SpecialPriceOffer specialPriceOffer = new SpecialPriceOffer("A", 2, 130);
        shoppingBillingSystem.addItem(new Product("A", 50, 3));
        shoppingBillingSystem.addItem(new Product("B", 30, 2));
        assertEquals(240, shoppingBillingSystem.getTotalPrice(shoppingBillingSystem.getItems(), specialPriceOffer), 0.0);
    }
}
