package com.sawant.fragment_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.buttonDialogFragment)
    Button buttonDialogFragment;
    @BindView(R.id.buttonAddFragment)
    Button buttonAddFragment;

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this, rootView);
        initializeresources();
        return rootView;
    }

    private void initializeresources() {
        buttonAddFragment.setOnClickListener(this);
        buttonDialogFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDialogFragment:
                addDialogFragment();

                break;
            case R.id.buttonAddFragment:
                addFragment();
                break;

        }
    }

    private void addFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NextFragment displayFragment = new NextFragment();
        fragmentTransaction.replace(R.id.main_layout, displayFragment, "DISPLAY");
        fragmentTransaction.addToBackStack(displayFragment.getClass().getName());
        fragmentTransaction.commitAllowingStateLoss();


    }

    private void addDialogFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag("dialog");

        if (fragment != null) {
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.addToBackStack(null);

        FragmentDialog fragmentDialog = new FragmentDialog();
        fragmentDialog.show(fragmentTransaction, "dialog");
    }

}
