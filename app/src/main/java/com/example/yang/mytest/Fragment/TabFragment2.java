package com.example.yang.mytest.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yang.mytest.Fragment.SetUp.ExitFragment;
import com.example.yang.mytest.Fragment.SetUp.ShouDongKongZhi;
import com.example.yang.mytest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment2 extends Fragment {

    @BindView(R.id.f2_l_gone)
    LinearLayout gone;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private ShouDongKongZhi shouDongKongZhi;

    private long exitTime;

    public TabFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
        ButterKnife.bind(this, view);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        shouDongKongZhi = new ShouDongKongZhi();

        return view;
    }

    @OnClick(value = {R.id.shoudongkongzhi, R.id.qinchuhuancun, R.id.banbengengx, R.id.guanyuwomen, R.id.my_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shoudongkongzhi:

                break;
            case R.id.qinchuhuancun:

                break;
            case R.id.banbengengx:

                break;
            case R.id.guanyuwomen:
                open(gone);
                break;
            case R.id.my_exit:
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    Toast.makeText(getContext(), "再按一次退出程序",
                            Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    Fragment exitFragment = new ExitFragment();
                    fm.beginTransaction().replace(R.id.tab, exitFragment).commit();
                }
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
