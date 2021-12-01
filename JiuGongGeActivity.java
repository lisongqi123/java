package com.example.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapp.datautil.RandomNums;
import com.example.myapp.gamebean.MyPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class JiuGongGeActivity extends Activity {
    private TextView[] textViews;//用数组声明九个方格
    private String TAG = "TAG";
    private MyPoint[] points;
    private int[] ids={R.id.tv_0,R.id.tv_1,R.id.tv_2,R.id.tv_3,R.id.tv_4,R.id.tv_5,R.id.tv_6,R.id.tv_7,R.id.tv_8,};//声明九个id
    LinearLayout lvContend;     //声明线性布局
    private static final  int TV_ACTION_ACTIVE = 0;
    private static final int TV_ACTION_NOMAL = 1;
    int width;
    private  int resultForTwo = 0;
    private  TextView tvResult;
    private  TextView tvResult0 ;
    int i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiugongge);
        initView();//初始化视图
        initPoint();
        setlisner();
    }
    private void initView(){//创建这个方法
        textViews = new TextView[9];
        for (int i = 0; i <ids.length; i++) {
            textViews[i]=findViewById(ids[i]);
            textViews[i].setTag(TV_ACTION_NOMAL);
        }
        lvContend = findViewById(R.id.lv_contend);
        tvResult0 =findViewById(R.id.res_1);
        tvResult = findViewById(R.id.tv_result0);
        getNewNums();
    }
    private void getNewNums(){
        RandomNums randomNums = new RandomNums();
        int[] data =   randomNums.getNums();
        resultForTwo = GetResult.get3Num(data);
        tvResult0.setText(resultForTwo+"");
        for (int i = 0; i < textViews.length; i++) {
            if(i%2==0){
                textViews[i].setText(data[i/2]+"");
            }

        }
    }

    private void initPoint(){
         width = dip2px(this,100);
        points = new MyPoint[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                MyPoint point = new MyPoint(width/2+j*width,width/2+i*width);
                points[i*3+j] = point;//将二位数组转换为一维，i为行，j为列，+j*width是当j取1和2时第1列，第2列的宽度，第0列j为0

            }
        }


    }
    private void setlisner(){
        lvContend.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: "+motionEvent.getX()+">>>>>>>>>>>>"+motionEvent.getY()+">>>>>>>>>>>>"+points[0].x+">>>>>>>>"+points[0].y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "onTouch: "+motionEvent.getX()+">>>>>>>>>>>>"+motionEvent.getY());
                        int x=(int)motionEvent.getX();//获取x坐标
                        int y=(int)motionEvent.getY();//获取y坐标
                        ArrayList<Integer> set = new ArrayList<>();//初始化set存放第一个数字
                        ArrayList<Integer> set1 = new ArrayList<>();//初始化set1存放第二个数字
                        for (int i = 0; i < 9; i++) {
                            int dis =(int) Math.sqrt((points[i].y-y)*(points[i].y-y)+(points[i].x-x)*(points[i].x-x));//计算两点间距离公式
                            set.add(dis);//存放距离
                            set1.add(dis);
                        }
                        Collections.sort(set);
                        int minDis = set.get(0) ;
                        int seletI=-1;
                        for(int i = 0;i<9;i++){
                            if(set1.get(i)==minDis){
                                seletI = i;
                            }
                        }
                        if(minDis>width/2){
                            return true;
                        }
                        if((int)textViews[seletI].getTag()==TV_ACTION_ACTIVE)
                        {
                            return true;
                        }
                        textViews[seletI].setBackgroundColor(Color.BLUE);
                        textViews[seletI].setTag(TV_ACTION_ACTIVE);
                        tvResult.append(textViews[seletI].getText().toString());

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d(TAG, "onTouch: "+motionEvent.getX()+">>>>>>>>>>>>"+motionEvent.getY());
                        String str = tvResult.getText().toString();//获取内容
                        String biaodashi1="[0-9][+,-][0-9]";
                        String biaodashi2="[0-9][+,-][0-9][+,-][0-9]";
                        String biaodashi3="[0-9][+,-][0-9][+,-][0-9][+,-][0-9]";
                        boolean tags1= Pattern.matches(biaodashi1,str);
                        boolean tags2= Pattern.matches(biaodashi2,str);
                        boolean tags3= Pattern.matches(biaodashi3,str);
                        int useResult = -1;
                        if(tags1){
                            if(str.charAt(1)=='+'){
                                useResult = Integer.parseInt(String.valueOf(str.charAt(0)))+Integer.parseInt(String.valueOf(str.charAt(2)));
                            }else if(str.charAt(1)=='-') {
                                useResult = Integer.parseInt(String.valueOf(str.charAt(0)))-Integer.parseInt(String.valueOf(str.charAt(2)));
                            }else{
                                Toast.makeText(JiuGongGeActivity.this,"您输入的格式有误，请重新输入",Toast.LENGTH_SHORT).show();//吐丝
                            }
                            if(useResult == resultForTwo&&tags1){
                                Toast.makeText(JiuGongGeActivity.this,"恭喜，游戏通关，即将进入下一关",Toast.LENGTH_SHORT).show();
                                getNewNums();
                            }else {
                                Toast.makeText(JiuGongGeActivity.this,"您的结果不对，请重新输入",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(JiuGongGeActivity.this,"您输入的格式有误，请重新输入",Toast.LENGTH_SHORT).show();//吐丝

                        }

                        tvResult.setText("");
                        reSetBackG();

                        break;


                }
                return true;
            }
        });

    }
    public static int dip2px(Context context, float dip) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dip * scale + 0.5);
    }
    public void reSetBackG(){
        for(int i = 0;i<9;i++){
            textViews[i].setTag(TV_ACTION_NOMAL);
            if(i%2==0){
                textViews[i].setBackgroundColor(Color.WHITE);
            }
            else {
                textViews[i].setBackgroundColor(Color.GREEN);
            }

        }
    }
    private void welcome (){
        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;

                while (true){
                    for( i = 0 ; i<textViews.length;i++){
                        try{
                            Thread.sleep(1000*i);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textViews[i].setBackgroundColor(Color.YELLOW);
                                }
                            });


                        }catch (Exception e){

                        }

                    }

                }

            }
        });
        thread.start();


    }
}
