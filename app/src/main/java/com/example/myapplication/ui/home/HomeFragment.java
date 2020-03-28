package com.example.myapplication.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.HomeActivity;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapters.InvoiceAdapter;
import com.example.myapplication.models.Invoice;
import com.example.myapplication.network.ApiClient;
import com.example.myapplication.network.FactsAfricaApi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private InvoiceAdapter invoiceAdapter;
    private List<Invoice> invoices;
    private View root;
    @BindView(R.id.invoiceRecycler) RecyclerView mInvoiceRecycler;
    @BindView(R.id.invoiceProgress) ProgressBar mInvoiceProgressBar;
    @BindView(R.id.refresh) SwipeRefreshLayout refresh;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        ((HomeActivity) getActivity()).authListener();
        loadInvoices();
        refresh.setOnRefreshListener(this::loadInvoices);
        return root;
    }

    /* Get all invoices */
    private void loadInvoices() {
        refresh.setRefreshing(true);
        FactsAfricaApi service = ApiClient.getClient().create(FactsAfricaApi.class);
        Call<List<Invoice>> call = service.getAllInvoices();
        call.enqueue(new Callback<List<Invoice>>() {
            @Override
            public void onResponse(Call<List<Invoice>> call, Response<List<Invoice>> response) {
                if (response.isSuccessful()) {
                    refresh.setRefreshing(false);
                    invoices = response.body();
                    mInvoiceProgressBar.setVisibility(View.GONE);
                    invoiceAdapter = new InvoiceAdapter(invoices, root.getContext());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
                    mInvoiceRecycler.setLayoutManager(layoutManager);
                    mInvoiceRecycler.setHasFixedSize(true);
                    mInvoiceRecycler.setAdapter(invoiceAdapter);
                    invoiceAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Invoice>> call, Throwable t) {
                refresh.setRefreshing(false);
                showErrorSnackbar();
            }
        });
    }

    /* Error snackbar */
    private void showErrorSnackbar() {
        View rootView = root;
        Snackbar snackbar = Snackbar.make(rootView,
                "Network error!.", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.design_default_color_error));
        snackbar.setAction("Retry", v -> {
            mInvoiceProgressBar.setVisibility(View.VISIBLE);
            loadInvoices();
            snackbar.dismiss();
        });
        snackbar.show();
    }

    /* Close Application */
    private void close() {
        getActivity().moveTaskToBack(true);
        getActivity().finish();
    }
}
