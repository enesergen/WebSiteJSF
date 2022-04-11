package Matrix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Category {

    int categoryId;
    String categoryName;
    Timestamp date;

    public Category() {
    }

    public Category(int categoryId, String categoryName, Timestamp date) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.date = date;
    }

    public Category(String categoryName, Timestamp date) {
        this.categoryName = categoryName;
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

   

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ArrayList<Category> getCategoryList() throws SQLException {
        ArrayList<Category> getCategoryList = new ArrayList<Category>();

        String sql = "select * from root.CATEGORY";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                getCategoryList.add(new Category(resultSet.getInt("CATEGORYID"), resultSet.getString("CATEGORYNAME"), resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally {
            connection.close();
        }
        return getCategoryList;

    }

    public void addCategory() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "INSERT INTO ROOT.CATEGORY (CATEGORYNAME, \"date\")  VALUES(?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, this.categoryName);
            statement.setTimestamp(2, DBDate.dbDate());
            statement.executeUpdate();

        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }

    }
    
    public void deleteCategory() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.CATEGORY where CATEGORYID=" + this.categoryId;
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
    
    public void deleteCategory(int categoryId) throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "delete from root.CATEGORY where CATEGORYID=" + categoryId;
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
        String sql = "UPDATE  ROOT.CATEGORY SET CATEGORYNAME=?, \"date\"=? WHERE CATEGORYID=" + this.getCategoryId();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, this.categoryName);
            statement.setTimestamp(2, DBDate.dbDate());

            statement.execute();
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        } finally {
            connection.close();
        }
    }
}
