package com.example.tanchishe;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.YELLOW;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int w;//宽
    int h;//高
    int i=0;
    int j=0;
    final static int TIME=500;//设置静态常量500毫秒（一分钟刷新两次）
    TextView[][] textViews = new TextView[20][20];//声明一个20，20的二维数组用来存储格子
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        w=getScreenSize()[0];//获取手机屏幕宽
        h=getScreenSize()[1];//0，1为下面return new int[] { screenWidth, screenHeigh };中数组的下标

        LinearLayout linearLayout = new LinearLayout(this);//初始化最外面的linearlayout
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(linearLayout);//最后一步，绑定视图（注意要放在初始化视图的后面）
        for (int i = 0; i < 20; i++) {//设置垂直的20个LinearLayout
            LinearLayout layout = new LinearLayout(this);//初始化垂直的linealayout
            ViewGroup.LayoutParams p=new LinearLayout.LayoutParams(w,h/20);//动态设置控件宽高;宽拉满,高为1/20
            layout.setLayoutParams(p);//将设置的p传入layout
            for (int j = 0; j < 20; j++) {//设置水平的20个TextView
                TextView textView = new TextView(this);
                ViewGroup.LayoutParams p1=new LinearLayout.LayoutParams(w/20,h/20);//动态设置控件宽高;宽为1/20,高为1/20
                textView.setLayoutParams(p1);//将设置的p1传入textView
                //textView.setText("1");//显示内容
                textView.setGravity(Gravity.CENTER);//设置居中
                textViews[i][j]=textView;//将每一个textView存储到二维数组里面
                layout.addView(textView);//将设置的文本内容添加到layout

            }
            linearLayout.addView(layout);//将20个垂直的Layout依次添加到最外面的linearLayout
        }
        startAn();//开启动画线程




    }
    public int[] getScreenSize() {
        DisplayMetrics dm = new DisplayMetrics();
        // 获取屏幕信息
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeigh = dm.heightPixels;

        return new int[] { screenWidth, screenHeigh };
    }
    private void bianLiang(TextView textView){//创建一个变量的方法，并传入TextView
        textView.setBackgroundColor(BLACK);//设置变化的颜色为黄色
        //变了后为了变回来做一个延迟，需要使用线程
        new Thread(new Runnable() {//括号中传入Runnable
            @Override
            public void run() {
                try {
                    Thread.sleep(TIME);//在子线程中设置沉睡，并抛异常
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //在Android中，ui线程只能被ui线程修改，子线程不能直接更改ui线程，使用runOnUiThread,线程套线程
                runOnUiThread(new Runnable() {//依旧传入new Runnable()
                    @Override
                    public void run() {//在这里将颜色改回来
                        textView.setBackgroundColor(Color.TRANSPARENT);//TRANSPARENT为将其改为父布局的颜色
                    }
                });
            }
        }).start();
    }
    private void startAn(){//开启动画
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){//注意别忘记while循环
                    try {
                        Thread.sleep(TIME);//在子线程中设置沉睡，并抛异常
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }//开启延时
                    i++;//由1到20从上往下亮(i为x，j为y)
                    if(i>=20){//当i大于等于20的时候
                        i=0;//将i还原成0
                    }
                    runOnUiThread(new Runnable() {//同上，子线程中不能修改主线程的ui
                        @Override
                        public void run() {
                            bianLiang(textViews[i][10]);//调用变量方法，传入textViews
                        }
                    });
                }
            }
        }).start();

    }

}