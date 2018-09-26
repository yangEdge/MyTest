package com.example.yang.mytest.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.yang.mytest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment3 extends Fragment {

    @BindView(R.id.f3_l_gone1)
    LinearLayout gone1;
    @BindView(R.id.f3_l_gone2)
    LinearLayout gone2;
    @BindView(R.id.f3_l_gone3)
    LinearLayout gone3;
    @BindView(R.id.f3_l_gone4)
    LinearLayout gone4;


    public TabFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_fragment3, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(value = {R.id.f3_ll1, R.id.f3_ll2, R.id.f3_ll3, R.id.f3_ll4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f3_ll1:
                open(gone1);
                break;
            case R.id.f3_ll2:
                open(gone2);
                break;
            case R.id.f3_ll3:
                open(gone3);
                break;
            case R.id.f3_ll4:
                open(gone4);
                break;
            default:
        }
    }

    private void open(LinearLayout ll) {
        if (ll.getVisibility() == View.GONE)
            ll.setVisibility(View.VISIBLE);
        else
            ll.setVisibility(View.GONE);
    }

}
