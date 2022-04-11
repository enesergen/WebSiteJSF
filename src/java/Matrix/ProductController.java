
package Matrix;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ProductController implements Serializable { 
    private Product product;
    private ArrayList<Product> productList;
    private ArrayList<Product> searchList;
    private String filter;
    
    
    public String discountless(Product product1){
        double price=(product1.getPrice()*12)/10;
        return String.valueOf(price);
    }
    

    public ArrayList<Product> getProductList() throws SQLException {
        product=new Product();
        productList=new ArrayList<Product>();
        productList=product.getProductList();
        return productList;
    }
    
    public String filter() throws SQLException {
        product=new Product();
        searchList=new ArrayList<Product>();
        searchList=product.getProductWithFilter(filter);
        return "search";
    }
    public String filter2() throws SQLException {
        product=new Product();
        searchList=new ArrayList<Product>();
        searchList=product.getProductWithFilter(filter);
        return "search2";
    }
    
    

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    
    
    
    //CRUD
    public String create() {
        try {
            this.product.addProduct();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }
    
    public String updateForm(Product product){
        this.product=product;
        return "updateProduct";
    }
    public String update(){
        try {
            this.product.updateProduct();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index";
    }
    public void delete(Product product){
        try {
           product.deleteProduct(product);
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public ProductController() {
        product=new Product();
        
    }


    public Product getProduct() {
        if(this.product==null){
            this.product=new Product();
        }
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public String productInformation(Product product){
        
        this.product=product;
        
        return "urunn.xhtml";
    }
    
    public String productInformation2(Product product){
        
        this.product=product;
        
        return "urunn2.xhtml";
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public ArrayList<Product> getSearchList() {
        return searchList;
    }

    public void setSearchList(ArrayList<Product> searchList) {
        this.searchList = searchList;
    }
    
    
    
}
