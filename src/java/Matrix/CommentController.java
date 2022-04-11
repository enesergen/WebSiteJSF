/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author AkinG
 */
@Named
@SessionScoped
public class CommentController implements Serializable {
    private Comment comment;
    private ArrayList<Comment> commentList;
     private ArrayList<Product> commentProduct; 
     
    public void addToCard(Product product) {
        commentProduct.add(product);
    }

    public ArrayList<Product> getCommentProduct() {
        if (this.commentProduct == null) {
            this.commentProduct = new ArrayList<Product>();
        }
        return commentProduct;
    }

    public void setCommentProduct(ArrayList<Product> commentProduct) {
        this.commentProduct = commentProduct;
    }
    

    public ArrayList<Comment> getCommentList() throws SQLException {
        comment=new Comment();
        commentList=comment.getCommentList();
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }
    public String create() {
        try {
            this.comment.addComment();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }

    public CommentController() {
        comment=new Comment();
    }

    public Comment getComment() {
        if(this.comment==null)
            this.comment=new Comment();
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
   
    

    
    
    
}
