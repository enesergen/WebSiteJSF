
package Matrix;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class OrderController implements Serializable {
    private Order order;
    private ArrayList<Order>orderList;
    private int orderId=-1;

   
    

    
    //bütün orderleri listeleme fonksiyonu
    public ArrayList<Order> fillArray() throws SQLException{
       orderList=new ArrayList<Order>();
       order=new Order();
       orderList=order.getOrderList();
       return orderList;
    }
    
    public ArrayList<Order>orderFilter(int id) throws SQLException{
        ArrayList<Order>filterOrder=new ArrayList<Order>();
        order=new Order();
        filterOrder=order.getOrderWithFilter(id);
        return filterOrder;
        
    }
    
    //burada order içindeki items lar list olur
    public String orderDetail(int id){
        orderId=id;     
        return "MyOrders2";
    }
    public ArrayList<OrderItems> oneOrderLItems() throws SQLException{
        OrderItems orderItems=new OrderItems();
        ArrayList<OrderItems>orderItemsList=new ArrayList<OrderItems>();
        orderItemsList=orderItems.getOneOrderItem(orderId);
        
        return orderItemsList;
    }
    
    public String removeOrder() throws SQLException{
        order=new Order();
        OrderItems orderItems=new OrderItems();
        order.deleteOrder(orderId);
        orderItems.deleteOrderItemsWithOrderId(orderId);
        return "index";
    }
    
    
    public OrderController() {
    }

    
     public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public Order getOrder() {
       if(this.order==null){
           this.order=new Order();
       }
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ArrayList<Order> getOrderList() {
        if(this.orderList==null){
            this.orderList=new ArrayList<Order>();
        }
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
    

   
    
    
}
