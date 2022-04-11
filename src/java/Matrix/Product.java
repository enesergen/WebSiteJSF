package Matrix;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;



public class Product {

    private int productId;          //ürün id
    private int categoryId;         // kategori id
    private int stock;              //stock miktarı
    private String brand;           //marka
    private String name;            //isim
    private String description;     //açıklama
    private String imgUrl;          //resim yolu
    private double price;           //fiyat
    private int counter=100;            //puan için sayaç
    private double point=100;           //müşteri puanlaması
    private Timestamp date;            //ürünün sisteme kayıt tarihi

    public Product() {              // boş contructor
    }

    //tam constructor
    public Product(int productId, int categoryId, int stock, String brand, String name, String description, String imgUrl, double price, int counter, double point, Timestamp date) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.stock = stock;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.counter = counter;
        this.point = point;
        this.date = date;
    }
    
    //id olmadan olan oluşturma da kullanabiliriz.
    public Product( int categoryId, int stock, String brand, String name, String description, String imgUrl, double price, int counter, double point, Timestamp date) {
        this.categoryId = categoryId;
        this.stock = stock;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.counter = counter;
        this.point = point;
        this.date = date;
    }

    //getter ve setter metodları
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    //Product için db ile gerçekleştirilebilen CRUD fonksiyonlar 
    public ArrayList<Product> getProductList() throws SQLException {//veritabanından tüm tabloyu çekiyoruz
        ArrayList<Product> productList = new ArrayList<Product>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.product";
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
                productList.add(new Product(resultSet.getInt("PRODUCTID"),
                        resultSet.getInt("CATEGORYID"),
                        resultSet.getInt("STOCK"),
                        resultSet.getString("BRAND"),
                        resultSet.getString("NAME"),
                        resultSet.getString("DESCRIPTION"),
                        resultSet.getString("IMGURL"),
                        resultSet.getDouble("PRICE"),
                        resultSet.getInt("COUNTER"),
                        resultSet.getDouble("POINT"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally{
            connection.close();
        }
        return productList;
    }

    public void addProduct() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "INSERT INTO ROOT.PRODUCT (CATEGORYID, STOCK, BRAND, \"NAME\", DESCRIPTION, IMGURL, PRICE, COUNTER, POINT, \"date\")  VALUES(?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet; 
        DBHelper dBHelper = new DBHelper();
        
        try {
            connection=dBHelper.getConnection();
            statement=connection.prepareStatement(sql);
            statement.setInt(1,this.categoryId);
            statement.setInt(2,this.stock);
            statement.setString(3,this.brand);
            statement.setString(4,this.name);
            statement.setString(5,this.description);
            statement.setString(6,this.imgUrl);
            statement.setDouble(7,this.price);
            statement.setInt(8,this.counter);
            statement.setDouble(9,this.point);
            statement.setTimestamp(10,DBDate.dbDate());
            statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally{
            connection.close();
        }

    }
    
    
    public void deleteProduct() throws SQLException{
        //Ortak DB bağlanma adımları
        String sql = "delete from root.product where PRODUCTID="+this.getProductId();
        Connection connection = null;
        Statement statement = null;
        //ResultSet resultSet;  -- gerek yok burda
        DBHelper dBHelper = new DBHelper();
        
        try {
            connection=dBHelper.getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connection.close();
        }
    }
    
    public void deleteProduct(Product product) throws SQLException{
        //Ortak DB bağlanma adımları
        String sql = "delete from root.product where PRODUCTID="+product.getProductId();
        Connection connection = null;
        Statement statement = null;
        //ResultSet resultSet;  -- gerek yok burda
        DBHelper dBHelper = new DBHelper();
        
        try {
            connection=dBHelper.getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connection.close();
        }
    }
    
    
    public void updateProduct() throws SQLException{
        
        //Ortak DB bağlanma adımları
        String sql = "UPDATE  ROOT.PRODUCT SET CATEGORYID=?, STOCK=?, BRAND=?, \"NAME\"=?, DESCRIPTION=?, IMGURL=?, PRICE=?, COUNTER=?, POINT=? WHERE PRODUCTID="+this.getProductId();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet; 
        DBHelper dBHelper = new DBHelper();
        
        try {
            connection=dBHelper.getConnection();
            statement=connection.prepareStatement(sql);
            statement.setInt(1,this.categoryId);
            statement.setInt(2,this.stock);
            statement.setString(3,this.brand);
            statement.setString(4,this.name);
            statement.setString(5,this.description);
            statement.setString(6,this.imgUrl);
            statement.setDouble(7,this.price);
            statement.setInt(8,this.counter);
            statement.setDouble(9,this.point);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connection.close();
        } 
    }
    
    //------KOŞULLU ISTEKLER--------- FİLTRELEMELER
    
    //isim marka veya açıklamadan filitreleme 
    
    public ArrayList<Product>getProductWithFilter(String filter) throws SQLException{
        String searchWord=filter;
        searchWord=searchWord.toUpperCase(new java.util.Locale("tr", "TR"));//burada hepsini büyük harf yapıyoruz.
        searchWord="%"+searchWord+"%";// db deki  like için başında sonunda yüzde
                                    // -bırakıyoruz bu içinde kelime görürse listeye atıyor 
        
        ArrayList<Product> productListWithFilter = new ArrayList<Product>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.product where (\"NAME\" LIKE ? OR BRAND LIKE ? OR DESCRIPTION LIKE ?) ";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();

        try {
            connection = dBHelper.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,searchWord);
            statement.setString(2,searchWord);
            statement.setString(3,searchWord);
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                //bir adet List için nesne oluşturup onu ekliyoruz.
                //burada nesne constructor ile ilk değer atamalarını db üzerinden yapıyoruz.
                productListWithFilter.add(new Product(resultSet.getInt("PRODUCTID"),
                        resultSet.getInt("CATEGORYID"),
                        resultSet.getInt("STOCK"),
                        resultSet.getString("BRAND"),
                        resultSet.getString("NAME"),
                        resultSet.getString("DESCRIPTION"),
                        resultSet.getString("IMGURL"),
                        resultSet.getDouble("PRICE"),
                        resultSet.getInt("COUNTER"),
                        resultSet.getDouble("POINT"),
                        resultSet.getTimestamp("date")));
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally{
            connection.close();
        }
        return productListWithFilter;
    }
    
    
    
    
    

}
