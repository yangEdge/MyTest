package com.example.yang.mytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.yang.mytest.Fragment.HelpTabFragment;
import com.example.yang.mytest.Fragment.HomeTabFragment;
import com.example.yang.mytest.Fragment.SettingTabFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

public class TabLayout extends FragmentActivity {

    private CommonTabLayout tl;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
    private String[] mTitle = {"首页", "设置", "帮助"};
    private int[] mIcon = {R.mipmap.shouye_lu, R.mipmap.shezhi_lu, R.mipmap.bangzhu_lu};
    private int[] mIconUn = {R.mipmap.b1, R.mipmap.b2, R.mipmap.b3};
    private HomeTabFragment tabFragment1 = new HomeTabFragment();
    private SettingTabFragment tabFragment2 = new SettingTabFragment();
    private HelpTabFragment tabFragment3 = new HelpTabFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        init();
        tl.setTabData(tabEntities, this, R.id.myFL, fragments);
        tl.setCurrentTab(0);
    }

    private void init() {
        tl = findViewById(R.id.tl);
        fragments.add(tabFragment1);
        fragments.add(tabFragment2);
        fragments.add(tabFragment3);

        for (int i = 0; i < mTitle.length; i++) {
            tabEntities.add(new TabEntity(mTitle[i], mIcon[i], mIconUn[i]));
        }
    }
}
