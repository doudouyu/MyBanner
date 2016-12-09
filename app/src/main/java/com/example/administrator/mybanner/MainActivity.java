package com.example.administrator.mybanner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends Activity {
    private CycleViewPager pager;
    private List<Integer> list = new ArrayList<>();
    private LinearLayout ll_point;
    private List<ImageView> list1;
    private Context _context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this._context = this;
        initView();
        initData();
        initListener();
    }
    protected void initView() {
//找到我们的ViewPager和指示点的容器
        pager = (CycleViewPager) findViewById(R.id.banner);
        ll_point = (LinearLayout) findViewById(R.id.ll_points);
    }
    @Override
    public void onStart() {
        super.onStart();
//当activity可见的时候开启轮播图
        addPoint();
        pager.startScroll();
    }
    @Override
    public void onStop() {
        super.onStop();
//不可见的时候关闭轮播图
        pager.stopScroll();
    }
    protected void initData() {
//初始化轮播图数据
        if (list.size() == 0) {
            list.add(R.drawable.picture1);
            list.add(R.drawable.picture2);
            list.add(R.drawable.picture3);
            list.add(R.drawable.picture4);
        }
        addPoint();
        pager.setAdapter(new MyItemPagerAdapter(_context, list));
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                Log.i("页面改变了", position + "");
                //当页面改变的时候，改变圆点的样式
                for (int i = 0; i < list1.size(); i++) {
                    if (i == position - 1) {
                        list1.get(i).setImageResource(R.drawable.selected_point);
                    } else {
                        list1.get(i).setImageResource(R.drawable.default_point);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    private void addPoint() {
        //存放imageView
        list1 = new ArrayList();
        list1.clear();
        ll_point.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
//遍历所有的指示点，设置当前被选中的页面的指示点为黑色，其他的为白色
            ImageView imageView = new ImageView(_context);
            if (i == 0) {
                imageView.setImageResource(R.drawable.selected_point);
            } else {
                imageView.setImageResource(R.drawable.default_point);
            }
            //给指示点添加一个间隔
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            list1.add(imageView);
            ll_point.addView(imageView, params);
        }
    }


    protected void initListener() {
        pager.setOnItemClickListener(new CycleViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //可以在这里做跳转的操作，当轮播图页面被点击的时候，就会响应
                Toast.makeText(_context, "跳转到详情界面", Toast.LENGTH_SHORT).show();
            }
        });
    }
}