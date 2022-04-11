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
public class CustomerController implements Serializable {
    

    
    
    private Customer customer;
    private ArrayList<Customer> customerList;

    public ArrayList<Customer> getCustomerList() throws SQLException {

        customer = new Customer();
        customerList = customer.getCustomerList();
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public String create() {
        try {
            this.customer.addCustomer();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "login11";
    }

    public String updateForm(Customer customer) {
        this.customer = customer;
        return "updateCustomer";
    }

    public String update() {
        try {
            this.customer.updateCustomer();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "bilgiler";
    }
    public String update(Customer customer) {
        try {
            this.customer=customer;
            this.customer.updateCustomer();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "profil2";
    }

    public void fillCustomer() throws SQLException {
        this.customerList = new ArrayList<Customer>();
        this.customerList = customer.getCustomerList();

        for (Customer customer1 : customerList) {
            if (this.customer.getEmail().equals(customer1.getEmail().toString()) && customer.getPassword().equals(customer1.getPassword().toString())) {
                this.customer.setCustomerId(customer1.getCustomerId());
                this.customer.setEmail(customer1.getEmail());
                this.customer.setPassword(customer1.getPassword());
                this.customer.setName(customer1.getName());
                this.customer.setLastName(customer1.getLastName());
                this.customer.setPhoneNumber(customer1.getPhoneNumber());
                this.customer.setSex(customer1.getSex());
                this.customer.setCity(customer1.getCity());
                this.customer.setAddress(customer1.getAddress());
                this.customer.setDate(customer1.getDate());

            }
        }
    }

    /*public String login() throws SQLException {
        fillCustomer();
        if (this.customer.loginCustomer()) {

            try {
                
                this.customer.loginCustomer();

            } catch (SQLException ex) {
                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }

            return "bilgiler.xhtml";
        } else {
            return "loginPage";
        }
    }
*/
    public CustomerController() {
        customer = new Customer();
    }

    public Customer getCustomer() {
        if (this.customer == null) {
            this.customer = new Customer();
        }
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
