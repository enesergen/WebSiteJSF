/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author AkinG
 */
public class Comment {
    private int commentId;
    private int productId;
    private int customerId;
    private String comment;
    private Timestamp date;

    public Comment() {
    }

    public Comment(int commentId, int productId, int customerId, String comment, Timestamp date) {
        this.commentId = commentId;
        this.productId = productId;
        this.customerId = customerId;
        this.comment = comment;
        this.date = date;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
     public ArrayList<Comment> getCommentList() throws SQLException {//veritabanından tüm tabloyu çekiyoruz
        ArrayList<Comment> commentList = new ArrayList<Comment>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.comment";
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
                commentList.add(new Comment(resultSet.getInt("COMMENTID"),
                        resultSet.getInt("PRODUCTID"),
                        resultSet.getInt("CUSTOMERID"),
                        resultSet.getString("COMMENT"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally{
            connection.close();
        }
        return commentList;
    }
    
    
    public void addComment() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "INSERT INTO ROOT.COMMENT (PRODUCTID, CUSTOMERID, COMMENT,  \"date\")  VALUES(?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet; 
        DBHelper dBHelper = new DBHelper();

        
        try {
            connection=dBHelper.getConnection();
            statement=connection.prepareStatement(sql);
           
          
            statement.setInt(1,this.productId);
            statement.setInt(2,this.customerId);
            statement.setString(3,this.comment);
            statement.setTimestamp(4,DBDate.dbDate());
            statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally{
            connection.close();
        }

    }
}
