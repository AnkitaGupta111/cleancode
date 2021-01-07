import com.shopping.ShoppingBillingSystem;
import com.shopping.models.Product;
import com.shopping.models.SpecialPriceOffer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class verify the price for two test cases.
 */
public class ShoppingBillingSystemTest {
    @Test
    public void testOnTwoDifferntItems() {
        ShoppingBillingSystem shoppingBillingSystem = new ShoppingBillingSystem();
        shoppingBillingSystem.addOffer(new SpecialPriceOffer("A", 3, 130));
        shoppingBillingSystem.addOffer(new SpecialPriceOffer("B", 2, 45));
        shoppingBillingSystem.addItem(new Product("A", 50, 3));
        shoppingBillingSystem.addItem(new Product("B", 30, 2));
        assertEquals(175, shoppingBillingSystem.getTotalPrice(shoppingBillingSystem.getItems(),
                shoppingBillingSystem.getOffers()), 0.0);
    }

    @Test
    public void testOnFourDifferntItems() {
        ShoppingBillingSystem shoppingBillingSystem = new ShoppingBillingSystem();
        shoppingBillingSystem.addOffer(new SpecialPriceOffer("A", 3, 130));
        shoppingBillingSystem.addOffer(new SpecialPriceOffer("B", 2, 45));
        shoppingBillingSystem.addItem(new Product("A", 50, 1));
        shoppingBillingSystem.addItem(new Product("B", 30, 1));
        shoppingBillingSystem.addItem(new Product("C", 20, 1));
        shoppingBillingSystem.addItem(new Product("D", 15, 1));
        assertEquals(115, shoppingBillingSystem.getTotalPrice(shoppingBillingSystem.getItems(),
                shoppingBillingSystem.getOffers()), 0.0);
    }
}
