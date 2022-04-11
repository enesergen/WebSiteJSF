package Matrix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


public class Order {

    int orderId;
    int customerId;
    int orderStatus=1;
    Timestamp date;

    public Order() {
    }

    public Order(int customerId, int orderStatus, Timestamp date) {
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.date = date;
    }

    public Order(int orderId, int customerId, int orderStatus, Timestamp date) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    //DB için CRUD fonksiyonları
    public ArrayList<Order> getOrderList() throws SQLException {//veritabanından tüm tabloyu çekiyoruz
        ArrayList<Order> orderList = new ArrayList<Order>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.ORDERS";
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
                orderList.add(new Order(resultSet.getInt("ORDERID"),
                        resultSet.getInt("CUSTOMERID"),
                        resultSet.getInt("ORDERSTATUS"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
        return orderList;
    }

    public void addOrder() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "INSERT INTO ROOT.ORDERS (CUSTOMERID, ORDERSTATUS, \"date\")  VALUES(?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, this.customerId);
            statement.setInt(2, this.orderStatus);
            statement.setTimestamp(3, DBDate.dbDate());
            statement.executeUpdate();

        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }

    }

    public void deleteOrder() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.ORDERS where ORDERID=" + this.orderId;
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

    public void deleteOrder(int id) throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.ORDERS where ORDERID=" + id;
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
    
    public ArrayList<Order> getOrderWithFilter(int customerId1) throws SQLException{
         ArrayList<Order> orderList1 = new ArrayList<Order>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.ORDERS WHERE CUSTOMERID="+customerId1;
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
                orderList1.add(new Order(resultSet.getInt("ORDERID"),
                        resultSet.getInt("CUSTOMERID"),
                        resultSet.getInt("ORDERSTATUS"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
        return orderList1;
    }
        
        
    

    public void updateOrder() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "UPDATE  ROOT.ORDERS SET  CUSTOMERID=?, ORDERSTATUS=? WHERE ORDERID=" + this.orderId;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, this.customerId);
            statement.setInt(2, this.orderStatus);
            statement.execute();
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }
    
    public void updateOrderStatusTo2() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "UPDATE  ROOT.ORDERS SET ORDERSTATUS=? WHERE ORDERID=" + this.orderId;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 2);
            statement.execute();
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }
    
    public void updateOrderStatusTo3() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "UPDATE  ROOT.ORDERS SET ORDERSTATUS=? WHERE ORDERID=" + this.orderId;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 3);
            statement.execute();
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }
    
    public void updateOrderStatusTo4() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "UPDATE  ROOT.ORDERS SET ORDERSTATUS=? WHERE ORDERID=" + this.orderId;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 4);
            statement.execute();
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }
    
    
     public void deleteOrderWithOrderItems() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.ORDERS where ORDERID=" + this.orderId;
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
        String sql1 = "delete from root.ORDERITEMS where ORDERID=" + this.orderId;
        Connection connection1 = null;
        Statement statement1 = null;
        //ResultSet resultSet1;  -- gerek yok burda
        DBHelper dBHelper1 = new DBHelper();
        
        try {
            connection1 = dBHelper1.getConnection();
            statement1 = connection1.createStatement();
            statement1.executeUpdate(sql1);
        } catch (SQLException ex) {
            dBHelper1.showErrorMessage(ex);
        } finally {
            connection1.close();
        }
        
    }
     
     public void deleteOrderWithOrderItems(int orderId) throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.ORDERS where ORDERID=" + orderId;
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
        String sql1 = "delete from root.ORDERITEMS where ORDERID=" + orderId;
        Connection connection1 = null;
        Statement statement1 = null;
        //ResultSet resultSet1;  -- gerek yok burda
        DBHelper dBHelper1 = new DBHelper();
        
        try {
            connection1 = dBHelper1.getConnection();
            statement1 = connection1.createStatement();
            statement1.executeUpdate(sql1);
        } catch (SQLException ex) {
            dBHelper1.showErrorMessage(ex);
        } finally {
            connection1.close();
        }
        
    }

}
