package com.example.myapplication.ui.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.ui.fragments.AllSavedFragment;
import com.example.myapplication.ui.fragments.BatchInvoiceFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedFragment extends Fragment {
    @BindView(R.id.savedViewPager) ViewPager mViewPager;
    @BindView(R.id.savedTabLayout) TabLayout mTabLayout;
    private AllSavedFragment allSavedFragment;
    private BatchInvoiceFragment batchInvoiceFragment;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SavedViewModel savedViewModel = ViewModelProviders.of(this).get(SavedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_saved, container, false);
        ButterKnife.bind(this, root);
        allSavedFragment = new AllSavedFragment();
        batchInvoiceFragment = new BatchInvoiceFragment();
        setFragments();
        setTabIcons();
        return root;
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private List<String> fragmentTitle;
        ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            fragments = new ArrayList<>();
            fragmentTitle = new ArrayList<>();
        }

        void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }

    /* Set fragments */
    private void setFragments() {
        mTabLayout.setupWithViewPager(mViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(allSavedFragment, "All Saved");
        viewPagerAdapter.addFragment(batchInvoiceFragment, "Batch Invoices");
        mViewPager.setAdapter(viewPagerAdapter);
    }

    /* Set icons */
    private void setTabIcons() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_cloud_download_black_24dp);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_playlist_add_black_24dp);
    }
}
