/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.ItemDetail;
import model.Order;

/**
 *
 * @author NAYOMI
 */
public class OrderController {

    public static boolean addOrder(Order order) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement stm = connection.prepareStatement("Insert into Orders values(?,?,?)");

            stm.setObject(1, order.getId());
            stm.setObject(2, order.getDate());
            stm.setObject(3, order.getCustomerId());
            boolean orderIsAdded = stm.executeUpdate() > 0;
            if (orderIsAdded) {
                boolean itemIsAdded = ItemDetailController.addItemDetails(order.getItemDetailList());
                if (itemIsAdded) {
                    boolean isUpdated = ItemController.updateStock(order.getItemDetailList());
                    if (isUpdated) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback(); //?
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
