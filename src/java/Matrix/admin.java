/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ENES
 */

@Named
@SessionScoped
public class admin implements Serializable{
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public admin() {
    }
    
    public String login(){
        if(password.equals("password")){
            return "index";
        }
        else{
            return "adminLogin";
        }
}

}
