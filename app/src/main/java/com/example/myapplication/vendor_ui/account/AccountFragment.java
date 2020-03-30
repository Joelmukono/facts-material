package com.example.myapplication.vendor_ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.HomeActivity;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.utils.FactsPreferences;
import com.google.android.material.button.MaterialButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.profileName) TextView mProfileName;
    @BindView(R.id.profileEmail) TextView mProfileEmail;
    @BindView(R.id.logoutButton) MaterialButton mLogoutButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, root);
        mLogoutButton.setOnClickListener(this);
        ((HomeActivity) getActivity()).authListener();
        setUserInfo();
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == mLogoutButton) {
            ((HomeActivity)getActivity()).logout();
            Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
            startActivity(loginIntent);
            getActivity().finish();
        }
    }

    /* Set User Name and email */
    private void setUserInfo() {
        mProfileName.setText(FactsPreferences.getUserName());
        mProfileEmail.setText(FactsPreferences.getUserEmail());
    }
}
