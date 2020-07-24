/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author NAYOMI
 */
public class IDController {
    public static String getLastID(String tableName,String colName) throws Exception{
        String SQL="select "+colName+" from "+tableName+" order by 1 desc limit 1";
        Connection conn = DBConnection.getDBConnection().getConnection();
        ResultSet rst = conn.createStatement().executeQuery(SQL);
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}
