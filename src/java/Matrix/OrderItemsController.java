package Matrix;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class OrderItemsController implements Serializable {

    private OrderItems orderItems;
    private ArrayList<OrderItems> orderItemsList;
    private ArrayList<Product> cartItem;//sepetteki ürünler site kapanana kadar geçerli
    private Customer customer;
    private Product product;
    
    public void addToCard(Product product) {
        this.product=new Product();
        this.product=product;
        cartItem.add(product);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void removeItem(Product product) {//sepetten çıkartmak
        ArrayList<Product> temp = new ArrayList<Product>();
        int c = 0;
        for (int i = 0; i < cartItem.size(); i++) {
            if (cartItem.get(i).getProductId() == product.getProductId() && c == 0) {
                c++;
            } else {
                temp.add(cartItem.get(i));
            }
        }
        cartItem.clear();
        cartItem = temp;
        temp = null;
    }

    public double sumOfCart() {
        double sum = 0;
        for (int i = 0; i < cartItem.size(); i++) {
            sum += cartItem.get(i).getPrice();
        }
        return sum;
    }

    public String payment(int id) throws SQLException {
        Order order = new Order(id, 1, DBDate.dbDate());
        Timestamp time = order.getDate();
        order.addOrder(); 
        ArrayList<Order>orderList1=new ArrayList<Order>();
        orderList1=order.getOrderList();
        for(int i=0;i<orderList1.size();i++){
            if(order.getCustomerId()==orderList1.get(i).getCustomerId() && order.getOrderStatus()==orderList1.get(i).getOrderStatus()){
                order.setOrderId(orderList1.get(i).getOrderId());       
            }
        }
        
        for (int i = 0; i < cartItem.size(); i++) {
            OrderItems orderItems1 = new OrderItems(order.getOrderId(), cartItem.get(i).getProductId(), 1, cartItem.get(i).getPrice(), 0, time);
            orderItems1.addOrderItems();
        }

        cartItem = new ArrayList<Product>();
        return "anasayfa2";
    }

    public OrderItemsController() {
        this.cartItem = new ArrayList<Product>();
    }

    public OrderItems getOrderItems() {
        if (this.orderItems == null) {
            this.orderItems = new OrderItems();
        }
        return orderItems;
    }

    public void setOrderItems(OrderItems orderItems) {
        this.orderItems = orderItems;
    }

    public ArrayList<OrderItems> getOrderItemsList() {
        if (this.orderItemsList == null) {
            this.orderItemsList = new ArrayList<OrderItems>();
        }
        return orderItemsList;
    }

    public void setOrderItemsList(ArrayList<OrderItems> orderItemsList) {
        if (this.orderItemsList == null) {
            this.orderItemsList = new ArrayList<OrderItems>();
        }
        this.orderItemsList = orderItemsList;
    }

    public ArrayList<Product> getCartItem() {
        if (this.cartItem == null) {
            this.cartItem = new ArrayList<Product>();
        }
        return cartItem;
    }

    public void setCartItem(ArrayList<Product> cartItem) {
        this.cartItem = cartItem;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
