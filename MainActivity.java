package com.example.daohangbiaoqian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;//初始化滑动界面的控件
    private PagerAdapter adapter;//该控件需要一个PagerAdapter
    private ArrayList<View> views;//声明PagerAdapter的数据源
    private int[] ids = {R.drawable.p1,R.drawable.p2,R.drawable.p3};//声明三张图片
    private int [] tids = {R.id.tv_point1,R.id.tv_point2,R.id.tv_point3};
    private  static final int POINT_ACTIVE = R.drawable.shape2;
    private  static final int POINT_NORMAL = R.drawable.shape1;
    private TextView []textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.vp_welcome);//绑定viewPager的id
        views = new ArrayList<>();//初始化ArrayList<View>
        textViews = new TextView[3];
        for (int i = 0; i < tids.length; i++) {
            textViews[i] = findViewById(tids[i]);
        }
        chageStu(0);
        LayoutInflater inflater= getLayoutInflater();//将xml转化为view需要通过打气筒
        for (int i = 0; i <ids.length ; i++) {//通过for循环打气并塞入数据
            View view = inflater.inflate(R.layout.item,null);//选第二个
            ImageView imageView =view.findViewById(R.id.iv_i);//接下来需要绑定图片和按钮
            imageView.setImageResource(ids[i]);//绑定三张图片
            if(i==2){//当第三张图片出现，设置按钮可见
                Button button =view.findViewById(R.id.bt_go);//绑定按钮id
                button.setVisibility(View.VISIBLE);//设置为可见
                button.setOnClickListener(new View.OnClickListener() {//设置按钮的监听，传入new View.OnClickListener()
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"正在开发中",Toast.LENGTH_SHORT).show();//内部内里面传入上下文需要加上类名
                    }
                });

            }
            views.add(view);

        }

        adapter = new PagerAdapter() {
            @Override//返回页卡条目
            public int getCount() {
                return views.size();
            }

            @Override//判断视图是否从对象中来
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull//新增页卡,将指定的页卡添加到container，并将其返回
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(views.get(position));
                return views.get(position);
            }

            @Override//销毁页卡
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
               // super.destroyItem(container, position, object);
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override//选中的时候
            public void onPageSelected(int position) {
                chageStu(position);//调用该方法
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void chageStu(int position){//创建一个改变状态的方法，传入高量
        for (int i = 0; i <textViews.length ; i++) {
            if(i==position){
                textViews[i].setBackgroundResource(POINT_ACTIVE);
            }else {
                textViews[i].setBackgroundResource(POINT_NORMAL);
            }
        }
    }
}