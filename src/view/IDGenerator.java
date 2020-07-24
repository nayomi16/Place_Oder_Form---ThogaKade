/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.IDController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NAYOMI
 */
public class IDGenerator {
    public static String getNewID(String tableName,String colName,String prifix) throws Exception{
        String lastID = IDController.getLastID(tableName,colName);
        System.out.println(lastID);
        if (lastID!=null) {
            int id=Integer.parseInt(lastID.split(prifix)[1]);
            id++;
            return prifix+id;
        }else{
            return prifix+"001";
        }
    }
}
