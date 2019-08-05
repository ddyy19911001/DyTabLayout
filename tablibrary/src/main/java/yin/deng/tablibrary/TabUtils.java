package yin.deng.tablibrary;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;



public class TabUtils {
    static TabUtils tabUtils;
    static int normalColor;
    static int selectColor;
    //单个tab对应页面的fragment（通用）
    static Class<? extends Fragment> pageFragment;
    /**
     * 添加fragment到布局中
     */
    public List<TextView> tvs = new ArrayList<>();//用于存储tab
    //当前显示的fragment
    private Fragment currentPageFragment;
    private int currentPosition;
    private TabUtils(){

    }

    public int getCurrentPosition() {
        return currentPosition;
    }


    public List<TextView> getTvs() {
        return tvs;
    }

    public void setTvs(List<TextView> tvs) {
        this.tvs = tvs;
    }

    public static TabUtils getInstance(Class<? extends Fragment> fragmentDotClass, int normalTextColor, int selectTextColor){
        if(tabUtils==null) {
             tabUtils = new TabUtils();
        }
        normalColor=normalTextColor;
        selectColor=selectTextColor;
        pageFragment=fragmentDotClass;
        return tabUtils;
    }

    public Fragment getCurrentPageFragment(){
        return currentPageFragment;
    }


    public void initPageFg(SmartTabLayout tab, ViewPager viewPager, final Context activity, String[] names, FragmentManager fragmentManager,int layoutId,int tvId) {
        FragmentPagerItems.Creator items = FragmentPagerItems.with(activity);
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Bundle bundle = new Bundle();
            bundle.putSerializable("name", name);
            bundle.putInt("position", i);
            items.add(name, pageFragment, bundle);
        }
        FragmentPagerItems c = items.create();
        final FragmentPagerItemAdapter pageAdapter = new FragmentPagerItemAdapter(
                fragmentManager, c);
        viewPager.setOffscreenPageLimit(names.length);
        viewPager.setAdapter(pageAdapter);
        tab.setCustomTabView(layoutId,tvId);
        tab.setViewPager(viewPager);
        tvs.clear();
        for (int i = 0; i < names.length; i++) {
            tvs.add((TextView) ((LinearLayout) tab.getTabAt(i)).getChildAt(0));
        }
        tvs.get(0).setTextColor(selectColor);
        tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clearTextColor();
                currentPosition=position;
                TextView tv = tvs.get(position);
                tv.setTextColor(selectColor);
                currentPageFragment = pageAdapter.getPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        currentPageFragment = pageAdapter.getPage(0);
    }

    public void initPageFg(List<Bundle> bundles,SmartTabLayout tab, ViewPager viewPager, final Context activity, String[] names, FragmentManager fragmentManager,int layoutId,int tvId) {
        if(bundles.size()!=names.length){
            throw new IllegalArgumentException("names number not equals bundles numbers!");
        }
        FragmentPagerItems.Creator items = FragmentPagerItems.with(activity);
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            bundles.get(i).putSerializable("name", name);
            bundles.get(i).putInt("position", i);
            items.add(name, pageFragment, bundles.get(i));
        }
        FragmentPagerItems c = items.create();
        final FragmentPagerItemAdapter pageAdapter = new FragmentPagerItemAdapter(
                fragmentManager, c);
        viewPager.setOffscreenPageLimit(names.length);
        viewPager.setAdapter(pageAdapter);
        tab.setCustomTabView(layoutId,tvId);
        tab.setViewPager(viewPager);
        tvs.clear();
        for (int i = 0; i < names.length; i++) {
            tvs.add((TextView) ((LinearLayout) tab.getTabAt(i)).getChildAt(0));
        }
        tvs.get(0).setTextColor(selectColor);
        tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clearTextColor();
                currentPosition=position;
                TextView tv = tvs.get(position);
                tv.setTextColor(selectColor);
                currentPageFragment = pageAdapter.getPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        currentPageFragment = pageAdapter.getPage(0);
    }

    public Fragment getCurrentSelectFragment(){
        return currentPageFragment;
    }




    public void clearTextColor() {
        for (int i = 0; i < tvs.size(); i++) {
            tvs.get(i).setTextColor(normalColor);
        }
    }
}
