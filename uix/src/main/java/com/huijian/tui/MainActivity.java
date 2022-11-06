package com.huijian.tui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.huijian.tui.adapters.ViewPagerAdapter;
import com.huijian.tui.ui.fragments.HomeFragment;
import com.huijian.tui.ui.fragments.MineFragment;
import com.huijian.tui.ui.fragments.ShoppingCarFragment;
import com.huijian.tui.ui.BaseActivity;
import com.mingrisoft.basedialog.LoadingDialog;
import com.mingrisoft.basedialog.MessageDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TitleBar titleBar;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void initView() {
        ImmersionBar.with(this).statusBarColor(R.color.blue).statusBarDarkFont(false).init();
        titleBar=findViewById(R.id.title_bar);
        titleBar.setTitle(R.string.bottom_title_home);
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                MessageDialog dialog=new MessageDialog(MainActivity.this);
                dialog.setMessage("好好学习");
                dialog.setConfirmClickListener("可以", dialog::dismiss);
                dialog.setCancelClickListener("不学", dialog::dismiss);
                dialog.show();
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                LoadingDialog dialog=new LoadingDialog(MainActivity.this);
                dialog.setMessage("正在加载中...");
                dialog.show();
            }
        });

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ShoppingCarFragment());
        fragmentList.add(new MineFragment());

        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        titleBar.setTitle(R.string.bottom_title_home);
                        bottomNavigationView.findViewById(R.id.menu_bottom_home).performClick();
                        break;
                    case 1:
                        titleBar.setTitle(R.string.bottom_title_shopping_car);
                        bottomNavigationView.findViewById(R.id.menu_bottom_shopping_car).performClick();
                        break;
                    case 2:
                        titleBar.setTitle(R.string.bottom_title_mine);
                        bottomNavigationView.findViewById(R.id.menu_bottom_mine).performClick();
                        break;
                    default:
                        break;
                }
                addAlphaAnim(titleBar.getTitleView());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("state状态"+state);
            }
        });

        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_bottom_home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.menu_bottom_shopping_car:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.menu_bottom_mine:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        if (name.contains("ViewPager")){
            return new ViewPager(context, attrs){
                @Override
                public boolean onInterceptTouchEvent(MotionEvent ev) {
                    final int action=ev.getAction() & MotionEvent.ACTION_MASK;
                    if (action==MotionEvent.ACTION_MOVE){
                        return false;
                    }else {
                        return super.onInterceptTouchEvent(ev);
                    }
                }
            };
        }else {
            return super.onCreateView(name, context, attrs);
        }
    }

    /**
     * 给控件添加透明度渐变的动画
     * @param view 要添加动画的控件
     * */
    private void addAlphaAnim(View view){
        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1f);
        alphaAnimation.setDuration(800);
        view.startAnimation(alphaAnimation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}