/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ItemDetail;

/**
 *
 * @author NAYOMI
 */
public class ItemDetailController {
    public static boolean addItemDetails(ArrayList <ItemDetail> itemDetailList) throws ClassNotFoundException, SQLException{
        for (ItemDetail itemDetail : itemDetailList) {
            if(!addItemDetails(itemDetail)){
                return false;
            }
        }
        return true;
    }

    public static boolean addItemDetails(ItemDetail itemDetail) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getDBConnection().getConnection().prepareStatement("Insert into ItemDetail values(?,?,?,?)");
        stm.setObject(1, itemDetail.getOrderId());
        stm.setObject(2, itemDetail.getItemCode());
        stm.setObject(3, itemDetail.getQty());
        stm.setObject(4, itemDetail.getUnitPrice());
        return stm.executeUpdate()>0;
    }
}
