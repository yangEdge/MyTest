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

import com.example.yang.mytest.Dialog.IpDialog;
import com.example.yang.mytest.Fragment.SetUp.ShouDongKongZhi;
import com.example.yang.mytest.Http.HttpUtils;
import com.example.yang.mytest.R;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 设置
 */
public class SettingTabFragment extends Fragment {

    @BindView(R.id.f2_l_gone)
    LinearLayout gone;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private ShouDongKongZhi shouDongKongZhi;

    private long exitTime;

    public SettingTabFragment() {
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
        ft.setCustomAnimations(R.anim.fragment_right,0);
        shouDongKongZhi = new ShouDongKongZhi();

        return view;
    }

    /**
     * ip地址设置
     */
    private void ipSet(){
        final IpDialog ipDialog = new IpDialog(getContext());
        ipDialog.setTitle("ip地址设置");
        ipDialog.setIp(HttpUtils.myUrl);

        ipDialog.setYesOnclickListener("是", new IpDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                String ip = ipDialog.getIp();
                String pattern = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))";
                if (Pattern.compile(pattern).matcher(ip).matches()){
                    HttpUtils.myUrl = ip;
                    Toast.makeText(getContext(), ip, Toast.LENGTH_SHORT).show();
                    ipDialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "格式错误，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ipDialog.setNoOnclickListener("否", new IpDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                ipDialog.dismiss();
            }
        });

        ipDialog.show();
    }

    @OnClick(value = {R.id.shoudongkongzhi, R.id.qinchuhuancun, R.id.ipSet, R.id.banbengengx, R.id.guanyuwomen, R.id.my_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shoudongkongzhi:
                Fragment shouDongKongZhi = new ShouDongKongZhi();
                ft.add(R.id.tab, shouDongKongZhi);
                ft.commitAllowingStateLoss();
                break;
            case R.id.qinchuhuancun:

                break;
            case R.id.ipSet:
                ipSet();
                break;
            case R.id.banbengengx:
                Toast.makeText(getActivity(), "已是最新版本", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "谢谢您的使用，下次再见", Toast.LENGTH_SHORT).show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                getActivity().finish();
                                System.exit(0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
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
