package Matrix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


public class OrderItems {

    private int itemId;
    private int orderId;
    private int productId;
    private int quantity=1;
    private double totalPrice;
    private int discount=0;
    private Timestamp date;

    public OrderItems() {
    }

    public OrderItems(int orderId, int productId, int quantity, double totalPrice, int discount, Timestamp date) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.date = date;
    }

    public OrderItems(int itemId, int orderId, int productId, int quantity, double totalPrice, int discount, Timestamp date) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    //DB için CRUD fonksiyonları
    public ArrayList<OrderItems> getOrderItemsList() throws SQLException {//veritabanından tüm tabloyu çekiyoruz
        ArrayList<OrderItems> orderItemList = new ArrayList<OrderItems>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.ORDERITEMS";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //bir adet List için nesne oluşturup onu ekliyoruz.
                //burada nesne constructor ile ilk değer atamalarını db üzerinden yapıyoruz.
                orderItemList.add(new OrderItems(resultSet.getInt("ITEMID"),
                        resultSet.getInt("ORDERID"),
                        resultSet.getInt("PRODUCTID"),
                        resultSet.getInt("QUANTITY"),
                        resultSet.getDouble("TOTALPRICE"),
                        resultSet.getInt("DISCOUNT"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
        return orderItemList;
    }
    
    public ArrayList<OrderItems> getOneOrderItem() throws SQLException {//veritabanından tüm tabloyu çekiyoruz
        ArrayList<OrderItems> orderItemList = new ArrayList<OrderItems>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.ORDERITEMS where ORDERID="+this.orderId;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //bir adet List için nesne oluşturup onu ekliyoruz.
                //burada nesne constructor ile ilk değer atamalarını db üzerinden yapıyoruz.
                orderItemList.add(new OrderItems(resultSet.getInt("ITEMID"),
                        resultSet.getInt("ORDERID"),
                        resultSet.getInt("PRODUCTID"),
                        resultSet.getInt("QUANTITY"),
                        resultSet.getDouble("TOTALPRICE"),
                        resultSet.getInt("DISCOUNT"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
        return orderItemList;
    }
    
    public ArrayList<OrderItems> getOneOrderItem(int id) throws SQLException {//veritabanından tüm tabloyu çekiyoruz
        ArrayList<OrderItems> orderItemList = new ArrayList<OrderItems>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.ORDERITEMS where ORDERID="+id;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                //bir adet List için nesne oluşturup onu ekliyoruz.
                //burada nesne constructor ile ilk değer atamalarını db üzerinden yapıyoruz.
                orderItemList.add(new OrderItems(resultSet.getInt("ITEMID"),
                        resultSet.getInt("ORDERID"),
                        resultSet.getInt("PRODUCTID"),
                        resultSet.getInt("QUANTITY"),
                        resultSet.getDouble("TOTALPRICE"),
                        resultSet.getInt("DISCOUNT"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
        return orderItemList;
    }
    
    
    

    public void addOrderItems() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "INSERT INTO ROOT.ORDERITEMS (ORDERID, PRODUCTID, QUANTITY, TOTALPRICE, DISCOUNT, \"date\")  VALUES(?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, this.orderId);
            statement.setInt(2, this.productId);
            statement.setInt(3, this.quantity);
            statement.setDouble(4, this.totalPrice);
            statement.setInt(5, this.discount);
            statement.setTimestamp(6, DBDate.dbDate());
            statement.executeUpdate();

        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }

    }

    public void deleteOrderItems() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.ORDERITEMS where ITEMID=" + this.itemId;
        Connection connection = null;
        Statement statement = null;
        //ResultSet resultSet;  -- gerek yok burda
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }

    public void updateOrderİtems() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "UPDATE  ROOT.ORDERITEMS SET ORDERID=?, PRODUCTID=?, QUANTITY=?, TOTALPRICE=?, DISCOUNT=?, \"date\"=? WHERE ITEMID=" + this.getItemId();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, this.orderId);
            statement.setInt(2, this.productId);
            statement.setInt(3, this.quantity);
            statement.setDouble(4, this.totalPrice);
            statement.setInt(5, this.discount);
            statement.setTimestamp(6, DBDate.dbDate());

            statement.execute();
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }

    public void deleteOrderItemsWithOrderId() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.ORDERITEMS where ORDERID=" + this.orderId;
        Connection connection = null;
        Statement statement = null;
        //ResultSet resultSet;  -- gerek yok burda
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }
    
    public void deleteOrderItemsWithOrderId(int id) throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.ORDERITEMS where ORDERID=" + id;
        Connection connection = null;
        Statement statement = null;
        //ResultSet resultSet;  -- gerek yok burda
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }

}
