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
import model.Item;
import model.ItemDetail;

/**
 *
 * @author NAYOMI
 */
public class ItemController {
    
    public static ArrayList<String> loadAllItemCode() throws Exception{
        String SQL="select code from Item";
        Connection conn = DBConnection.getDBConnection().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<String> itemCodeList = new ArrayList<>();
        while (rst.next()) {  
            itemCodeList.add(rst.getString(1));
        }
        return itemCodeList;
    }
    
    public static Item searchItem(String code) throws Exception{
        String SQL="select * from Item where code='"+code+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        ResultSet rst = conn.createStatement().executeQuery(SQL);
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4)
            );
        } else {
            return null;
        }
    }

    public static boolean updateStock(ArrayList<ItemDetail> itemDetailList) throws ClassNotFoundException, SQLException {
        for (ItemDetail itemDetail : itemDetailList) {
            if(!updateStock(itemDetail)){
                return false;
            }
        }
        return true;
    
    }

    public  static boolean updateStock(ItemDetail itemDetail) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().prepareStatement("Update Item set qtyOnHand=qtyOnHand-? where code=?");
        stm.setObject(1, itemDetail.getQty());
        stm.setObject(2, itemDetail.getItemCode());
        return stm.executeUpdate()>0;
    }
}
