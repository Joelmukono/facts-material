package com.example.myapplication.vendor_ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.VendorActivity;
import com.example.myapplication.adapters.SavedInvoiceAdapter;
import com.example.myapplication.models.Invoice;
import com.example.myapplication.network.ApiClient;
import com.example.myapplication.network.FactsAfricaApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllSavedFragment extends Fragment {
    private SavedInvoiceAdapter savedInvoiceAdapter;
    @BindView(R.id.savedRecycler) RecyclerView mInvoiceRecycler;
    @BindView(R.id.refresh) SwipeRefreshLayout refresh;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_all_saved, container, false);
        ButterKnife.bind(this, root);
        ((VendorActivity) getActivity()).authListener();
        return root;
    }

    private void loadInvoices() {
        FactsAfricaApi service = ApiClient.getClient().create(FactsAfricaApi.class);
        Call<List<Invoice>> call = service.getSavedInvoices();
        call.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {

            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {

            }
        });
    }
}
