package example.kata01;

import example.kata01.order.OrderMgr;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        OrderMgr orderMgr = new OrderMgr();
        orderMgr.addItem(1, 1, 50);
        orderMgr.calcOrder();
    }
}
