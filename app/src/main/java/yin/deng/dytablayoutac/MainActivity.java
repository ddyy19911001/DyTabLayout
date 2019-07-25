package yin.deng.dytablayoutac;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import yin.deng.tablibrary.TabUtils;

public class MainActivity extends AppCompatActivity {
    private SmartTabLayout hmTabLayout;
    private TextView tvLineTab;
    private ViewPager viewpager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFirst();
    }


    protected void initFirst() {
        hmTabLayout = (SmartTabLayout) findViewById(R.id.hm_tab_layout);
        tvLineTab = (TextView) findViewById(R.id.tv_line_tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        TabUtils.getInstance(MyTestFragment.class,R.color.dy_them_color_blue
                ,R.color.dy_them_color_F99).
                initPageFg(hmTabLayout,viewpager,this,
                        new String[]{"tab1","tab2","tab3","tab4","tab5"
                                ,"tab6","tab7","tab8","tab9","tab10"},
                        getSupportFragmentManager(),R.layout.hm_custom_tab_text,
                        R.id.tv_tab);
    }
}
