/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author NAYOMI
 */
public class Order {
    private String id; //orderId;
    private String date; //OrderDate
    private String customerId;
    private ArrayList<ItemDetail >itemDetailList;

    public Order() {
    }

    public Order(String id, String date, String customerId, ArrayList<ItemDetail> itemDetailList) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
        this.itemDetailList = itemDetailList;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the itemDetailList
     */
    public ArrayList<ItemDetail > getItemDetailList() {
        return itemDetailList;
    }

    /**
     * @param itemDetailList the itemDetailList to set
     */
    public void setItemDetailList(ArrayList<ItemDetail > itemDetailList) {
        this.itemDetailList = itemDetailList;
    }
    
    
}
