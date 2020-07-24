/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Customer;

/**
 *
 * @author NAYOMI
 */
public class CustomerController {
    
    public static ArrayList<String> loadAllCustomerID() throws ClassNotFoundException, SQLException{
        String SQL="select id from Customer";
        Connection conn = DBConnection.getDBConnection().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<String> custIdList = new ArrayList<>();
        while (rst.next()) {                
            custIdList.add(rst.getString(1));
       }
        return custIdList;
    }
    
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
        String SQL="select * from Customer where id='"+id+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        ResultSet rst = conn.createStatement().executeQuery(SQL);
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
        } else {
            return null;
        }
    }
public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into Customer Values(?,?,?,?)");
        stm.setObject(1,customer.getId() );
        stm.setObject(2,customer.getName());
        stm.setObject(3,customer.getAddress());
        stm.setObject(4,customer.getSalary());
        int res=stm.executeUpdate();
        return res>0;
    }
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update Customer set name=?, address=?, salary=? where id=?");
        stm.setObject(1,customer.getName());
        stm.setObject(2,customer.getAddress());
        stm.setObject(3,customer.getSalary());
        stm.setObject(4,customer.getId() );
        int res=stm.executeUpdate();
        return res>0;
    }
   
    public static  boolean  deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getDBConnection().getConnection().createStatement().executeUpdate("Delete From Customer where id='"+id+"'")>0;
    }
    public static ArrayList<Customer>getAllCustomer() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getDBConnection().getConnection().createStatement().executeQuery("Select * From Customer");
        ArrayList<Customer> customerList=new ArrayList<>();
        while(rst.next()){
            Customer customer=new Customer(rst.getString("id"),rst.getString("name"), rst.getString("address"),rst.getDouble("salary"));
            customerList.add(customer);
        }
        return customerList;
    }    
}
