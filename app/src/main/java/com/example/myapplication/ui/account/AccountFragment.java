package com.example.myapplication.ui.account;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.utils.FactsPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountFragment extends Fragment {
    @BindView(R.id.profileName) TextView mProfileName;
    @BindView(R.id.profileEmail) TextView mProfileEmail;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, root);
        setUserInfo();
        return root;
    }

    /* Set User Name and email */
    private void setUserInfo() {
        mProfileName.setText(FactsPreferences.getUserName());
        mProfileEmail.setText(FactsPreferences.getUserEmail());
    }
}
