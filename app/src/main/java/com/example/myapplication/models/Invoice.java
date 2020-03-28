
package com.example.myapplication.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Invoice implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("supplier_id")
    @Expose
    private Integer supplierId;
    @SerializedName("buyer_id")
    @Expose
    private Integer buyerId;
    @SerializedName("invoice_status")
    @Expose
    private Integer invoiceStatus;
    @SerializedName("invoice_amount")
    @Expose
    private String invoiceAmount;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private final static long serialVersionUID = 9150701147035518491L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Invoice() {
    }

    /**
     * 
     * @param createdAt
     * @param supplierId
     * @param dueDate
     * @param invoiceAmount
     * @param id
     * @param buyerId
     * @param invoiceStatus
     * @param updatedAt
     */
    public Invoice(Integer id, Integer supplierId, Integer buyerId, Integer invoiceStatus, String invoiceAmount, String dueDate, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.supplierId = supplierId;
        this.buyerId = buyerId;
        this.invoiceStatus = invoiceStatus;
        this.invoiceAmount = invoiceAmount;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }




  public String convertStatus(){
        String status = getInvoiceStatus().toString();

        if(status.equals("1")){
            status = "pending";
        }else if(status.equals("2")){
            status = "approved";
        }else{
            status = "declined";
        }
        return status;
}



    @NonNull
    @Override
    public String toString() {

        return this.invoiceAmount; ///selects many properties form model
    }

}
