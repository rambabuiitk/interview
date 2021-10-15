import java.util.Comparator;
import java.util.PriorityQueue;

public class BuySellOrders {

    public static void main(String[] args) {
        String[][] orders = {
                {"150", "5", "buy"},
                {"190", "1", "sell"},
                {"200", "1", "sell"},
                {"100", "9", "buy"},
                {"140", "8", "sell"},
                {"210", "4", "buy"}};
        BuySellOrders buySellOrders = new BuySellOrders();
        System.out.println(buySellOrders.order_book(orders));
    }

    public int order_book(String[][] orders) {
        if (orders == null || orders.length == 0 || orders[0].length == 0) {
            return 0;
        }

        int result = 0;
        PriorityQueue<Order> buyPq = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);
        PriorityQueue<Order> sellPq = new PriorityQueue<>(Comparator.comparingInt(o -> o.price));

        for (int i =0;i<orders.length;i++) {
            int price = Integer.parseInt(orders[i][0]);
            int qty = Integer.parseInt(orders[i][1]);

            if ("buy".equals(orders[i][2])) {
                Order buyOrder = new Order(price, qty);
                while (!sellPq.isEmpty() && sellPq.peek().price <= buyOrder.price && buyOrder.qty > 0) {
                    Order sellOrder = sellPq.poll();
                    if (buyOrder.qty < sellOrder.qty) {
                        result += buyOrder.qty;
                        sellOrder.qty = sellOrder.qty - buyOrder.qty;
                        sellPq.offer(sellOrder);
                        buyOrder.qty = 0;
                    } else {
                        result += sellOrder.qty;
                        buyOrder.qty -= sellOrder.qty;
                    }
                }

                if (buyOrder.qty > 0) {
                    buyPq.offer(buyOrder);
                }
            } else {
                Order sellOrder = new Order(price, qty);
                while (!buyPq.isEmpty() && buyPq.peek().price >= sellOrder.price && sellOrder.qty > 0) {
                    Order buyOrder = buyPq.poll();
                    if (sellOrder.qty < buyOrder.qty) {
                        result += sellOrder.qty;
                        buyOrder.qty -= sellOrder.qty;
                        buyPq.offer(buyOrder);
                        sellOrder.qty = 0;
                    } else {
                        result += buyOrder.qty;
                        sellOrder.qty -= buyOrder.qty;
                    }
                }

                if (sellOrder.qty > 0) {
                    sellPq.offer(sellOrder);
                }
            }
        }


        return result;
    }

    private class Order {
        public int price;
        public int qty;

        public Order(int price, int qty) {
            this.price = price;
            this.qty = qty;
        }
    }
}
