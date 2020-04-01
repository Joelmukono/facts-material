package com.example.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Invoice;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> implements Filterable {
    private List<Invoice> invoiceList;
    private List<Invoice> invoiceListAll;

    public InvoiceAdapter(List<Invoice> invoiceList, Context context) {
        this.invoiceList = invoiceList;
        this.invoiceListAll = new ArrayList<>(invoiceList);
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_list, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        holder.bindInvoice(invoiceList.get(position));
    }

    @Override
    public int getItemCount() {
        return invoiceList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Invoice> filteredList = new ArrayList<>();

            if(constraint.toString().isEmpty()){
                filteredList.addAll(invoiceListAll);
            }else {
                for(Invoice invoice:invoiceListAll){
                    if(invoice.convertStatus().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(invoice);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            invoiceList.clear();
            invoiceList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };

    static class InvoiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.invoiceNumber) TextView mInvoiceNumber;
        @BindView(R.id.dueDate) TextView mDueDate;
        @BindView(R.id.recipient) TextView mRecipient;
        @BindView(R.id.invoiceAmount) TextView mInvoiceAmount;
        @BindView(R.id.invoiceStatus) TextView mInvoiceStatus;

        InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindInvoice(Invoice invoice) {
            String status;
            /* Filter status colors */
            if(invoice.getInvoiceStatus().toString().equals("1")){
                status = "Pending";
                mInvoiceStatus.setTextColor(Color.parseColor("#ECB753"));
                mInvoiceStatus.setText(String.format("Status: %s", status));
            } else if(invoice.getInvoiceStatus().toString().equals("2")){
                status = "Approved";
                mInvoiceStatus.setTextColor(Color.parseColor("#0B6623"));
                mInvoiceStatus.setText(String.format("Status: %s", status));
            } else if(invoice.getInvoiceStatus().toString().equals("3")){
                status = "Rejected";
                mInvoiceStatus.setTextColor(Color.parseColor("#FF0000"));
                mInvoiceStatus.setText(String.format("Status: %s", status));
            }

            mInvoiceAmount.setText(String.format("Invoice Amount: %s", invoice.getInvoiceAmount()));
            mInvoiceNumber.setText(String.format("Invoice No: #%s", invoice.getId().toString()));
            mDueDate.setText(String.format("Due Date: %s", invoice.getDueDate()));
            mRecipient.setText(invoice.getBuyerId().toString());
        }
    }
}
