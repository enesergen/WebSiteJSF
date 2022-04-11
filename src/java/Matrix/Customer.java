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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped

public class Customer implements Serializable {

    private int loginState=0;

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }
    
    private int customerId;         // müşteri id
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber=" ";
    private String sex="ERKEK";
    private String city="ISTANBUL";
    private String address="ISTANBUL";
    private Timestamp date;            //db için kayıt tarihi

    public Customer() { //boş constructor
    }

    //tam constructor
    public Customer(int customerId, String email, String password, String name, String lastName, String phoneNumber, String sex, String city, String address, Timestamp date) {
        this.customerId = customerId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.city = city;
        this.address = address;
        this.date = date;
    }
    
    public String payment(){
        if(loginState==0){
            return "login11";
            
        }else{
             return "MyCart2";
        }
    }
    
    //getter ve setterlar
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    public ArrayList<Customer> getCustomerList() throws SQLException {//veritabanından tüm tabloyu çekiyoruz
        ArrayList<Customer> customerList = new ArrayList<Customer>();//Veritabanından çekilen tüm tabloları indexlerde tutuyoruz
        //Ortak DB bağlanma adımları
        String sql = "select * from root.customer";
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
               
                customerList.add(new Customer (resultSet.getInt("CUSTOMERID"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("NAME"),
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("PHONENUMBER"),
                        resultSet.getString("SEX"),
                        resultSet.getString("CITY"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getTimestamp("date")));
                 
            }
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally{
            connection.close();
        }
        return customerList;
    }
    public void addCustomer() throws SQLException {
        //Ortak DB bağlanma adımları
        String sql = "INSERT INTO ROOT.CUSTOMER (EMAIL, PASSWORD, \"NAME\", LASTNAME, PHONENUMBER, SEX, CITY, ADDRESS, \"date\")  VALUES(?,?,?,?,?,?,?,?,?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet; 
        DBHelper dBHelper = new DBHelper();

        
        try {
            connection=dBHelper.getConnection();
            statement=connection.prepareStatement(sql);
           
            statement.setString(1,this.email);
            statement.setString(2,this.password);
            statement.setString(3,this.name);
            statement.setString(4,this.lastName);
            statement.setString(5,this.phoneNumber);
            statement.setString(6,this.sex);
            statement.setString(7,this.city);
            statement.setString(8,this.address);
            statement.setTimestamp(9,DBDate.dbDate());
            statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            dBHelper.showErrorMessage(ex);
        }finally{
            connection.close();
        }

    }
     public String loginCustomer() throws SQLException {
        
        int flag=1;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        DBHelper dBHelper = new DBHelper();
        try{
//            connection = dBHelper.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sql);
        connection = dBHelper.getConnection();
        PreparedStatement st=connection.prepareStatement("select * from ROOT.CUSTOMER ");
        ResultSet rs= st.executeQuery();
          while(rs.next())
          {
        if(rs.getString("EMAIL").equals(email)&& rs.getString("PASSWORD").equals(password)){
            this.customerId=rs.getInt("CUSTOMERID");
            this.email=rs.getString("EMAIL");
            this.password=rs.getString("PASSWORD");
            this.name=rs.getString("NAME");
            this.lastName=rs.getString("LASTNAME");
            this.phoneNumber=rs.getString("PHONENUMBER");
            this.sex=rs.getString("SEX");
            this.city=rs.getString("CITY");
            this.address=rs.getString("ADDRESS");
            this.date=rs.getTimestamp("date");
            
            
            
            flag=0;
        break;
        }
           
        } 
        }catch (SQLException ex) { 
                }
        if(flag==0){
           FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Correct Username and Passowrd",
							"Succesful Login"));
         
           
           
         loginState=1;
         return "anasayfa2";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
         
         return "login11";
        }
    }
      public void fillCustomer() throws SQLException {
         ArrayList<Customer>temp = new ArrayList<Customer>();
         temp= this.getCustomerList();

            for (Customer customer1 : temp) {
            if (this.getEmail().equals(customer1.getEmail().toString()) && this.getPassword().equals(customer1.getPassword().toString())) {
                this.setCustomerId(customer1.getCustomerId());
                this.setEmail(customer1.getEmail());
                this.setPassword(customer1.getPassword());
                this.setName(customer1.getName());
                this.setLastName(customer1.getLastName());
                this.setPhoneNumber(customer1.getPhoneNumber());
                this.setSex(customer1.getSex());
                this.setCity(customer1.getCity());
                this.setAddress(customer1.getAddress());
                this.setDate(customer1.getDate());

            }
        }
    } 
     
     
     
     
     
     
     
     
     public void updateCustomer() throws SQLException{
        
        //Ortak DB bağlanma adımları
        String sql = "UPDATE  ROOT.CUSTOMER SET  EMAIL=?, PASSWORD=?, \"NAME\"=?, LASTNAME=?, PHONENUMBER=?, SEX=?, CITY=?, ADDRESS=? WHERE CUSTOMERID="+this.customerId;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet; 
        DBHelper dBHelper = new DBHelper();
        
        try {
            connection=dBHelper.getConnection();
            statement=connection.prepareStatement(sql);
            
            statement.setString(1,this.email);
            statement.setString(2,this.password);
            statement.setString(3,this.name);
            statement.setString(4,this.lastName);
            statement.setString(5,this.phoneNumber);
            statement.setString(6,this.sex);
             statement.setString(7,this.city);
            statement.setString(8,this.address);
            
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connection.close();
        } 
    }
     
}
