package com.example.tanchishe;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.TRANSPARENT;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanchishe.bean.IJ;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {//实现点击事件的接口
    int w;//宽
    int h;//高
    int i=0;//左右
    int count;
    int j=0;//上下
    int tag;
    static int foodTime = 300;//一截身体的长度
//    IJ oldIJ;
    int[]xy;//用来存储果实坐标
    //ArrayList<IJ> snakeList;//蛇的长度
    final static int TIME=300;//设置静态常量速度300毫秒
    ImageView iv_u,iv_d,iv_l,iv_r;
    private TextView score;
    TextView[][] textViews = new TextView[30][20];//声明一个30行，20列的二维数组用来存储格子
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        snakeList =new ArrayList<>();//实例化对象
//        snakeList.add(new IJ(1,1));
        w=getScreenSize()[0];//获取手机屏幕宽
        h=getScreenSize()[1];//0，1为下面return new int[] { screenWidth, screenHeigh };中数组的下标

        LinearLayout linearLayout =findViewById(R.id.ll_game);//初始化最外面的linearlayout
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 30; i++) {//设置垂直的40个LinearLayout
            LinearLayout layout = new LinearLayout(this);//初始化垂直的linealayout
            ViewGroup.LayoutParams p=new LinearLayout.LayoutParams(w,h/30);//动态设置控件宽高;宽拉满,高为1/40
            layout.setLayoutParams(p);//将设置的p传入layout
            for (int j = 0; j < 20; j++) {//设置水平的20个TextView
                TextView textView = new TextView(this);
                ViewGroup.LayoutParams p1=new LinearLayout.LayoutParams(w/20,h/30);//动态设置控件宽高;宽为1/20,高为1/20
                textView.setLayoutParams(p1);//将设置的p1传入textView
                //textView.setText("1");//显示内容
                textView.setGravity(Gravity.CENTER);//设置居中
                textViews[i][j]=textView;//将每一个textView存储到二维数组里面
                layout.addView(textView);//将设置的文本内容添加到layout

            }
            linearLayout.addView(layout);//将40个垂直的Layout依次添加到最外面的linearLayout
        }
        startAn();//开启动画线程
        xy = food();//将生成的果实坐标放到xy
        initView();



    }


     int[] food() {
         Random random = new Random();
         int i = random.nextInt(29);//i为行，j为列
         int j = random.nextInt(19);
         textViews[i][j].setBackgroundResource(R.drawable.guoshi);
         return new int[]{i,j};//返回i，j的值
    }



    private void initView() {
        score = findViewById(R.id.tv_score);
        iv_u = findViewById(R.id.iv_up);//绑定id
        iv_d = findViewById(R.id.iv_down);
        iv_l = findViewById(R.id.iv_left);
        iv_r = findViewById(R.id.iv_right);
        iv_u.setOnClickListener(this);//设置监听
        iv_d.setOnClickListener(this);
        iv_l.setOnClickListener(this);
        iv_r.setOnClickListener(this);
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
        textView.setBackgroundResource(R.drawable.shape2);//设置蛇

        //变了后为了变回来做一个延迟，需要使用线程
        new Thread(new Runnable() {//括号中传入Runnable
            @Override
            public void run() {
                try {
                    Thread.sleep(foodTime);//在子线程中设置沉睡，并抛异常（身体长度）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //在Android中，ui线程只能被ui线程修改，子线程不能直接更改ui线程，使用runOnUiThread,线程套线程
                runOnUiThread(new Runnable() {//依旧传入new Runnable()
                    @Override
                    public void run() {//在这里将颜色改回来
                        textView.setBackgroundColor(TRANSPARENT);//TRANSPARENT为将其改为父布局的颜色
                    }
                });
            }
        }).start();
    }

    private void startAn(){//开启动画
        new Thread(new Runnable() {//i为上下，j为左右
            @Override
            public void run() {
                //snakeList.get(0).
                i = (int) (1+Math.random()*(29-1+1));//随机生成蛇一开始的位置
                //snakeList.get(0).
                j = (int) (1+Math.random()*(19-1+1));

                while(true){//注意别忘记while循环
                    try {
                        Thread.sleep(TIME);//在子线程中设置沉睡，并抛异常(移动速度)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }//开启延时

                    changeByDate();//控制移动的方法
                   // Log.d("TAG", "run: "+i+">>>>>>>>>>>>>>>>"+j);
                    if(i>29){//当蛇跑到下面去时
                        i=0;//将它放回上面
                    }
                    if(i<0){//当蛇跑到上面时
                       i=29;//将它放回下面
                    }
                    if(j>19){//跑右边
                        j=0;//还原到左边
                    }
                    if(j<0){//左边
                        j=19;//放回右边
                    }
                    runOnUiThread(new Runnable() {//同上，子线程中不能修改主线程的ui
                        @Override
                        public void run() {
                            if(xy[0]==i&&xy[1]==j){
                                xy=food();//吃到果实，再次调用吃果实方法随机生成果实
                                count++;//积分+1
                                score.setText("积分："+count);
//                                if(flag==true){
//                                    beLong();
//                                }
                                foodTime +=300;//身体变长
                            }
                            bianLiang(textViews[i][j]);//调用变量方法，传入textViews

                        }
                    });
                }
            }
        }).start();

    }
//    private void beLong(){//变长的方法
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    try {
//                        Thread.sleep(300);
//                        flag=false;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//            }
//        });
//    }

    private void changeByDate() {//通过这个方法来控制移动

        switch(tag){
            case 0:
                i--;//

                break;
            case 1:
                i++;

                break;
            case 2:
                j--;

                break;
            case 3:
                j++;

                break;
        }
//        if(flag){
//            flag = false;
//            return;
//        }

//        for (int i = snakeList.size()-2; i >=0; i--) {
//            snakeList.set(i+1,snakeList.get(i));//将0号给一号，后面给前面的，0号元素不处理，数值上的移动
//            Log.d("TAG", "changeByDate: "+i+snakeList+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        }

    }

    @Override//点击事件
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.iv_up:
                tag=0;
                break;
            case R.id.iv_down:
                tag=1;
                break;
            case R.id.iv_left:
                tag=2;
                break;
            case R.id.iv_right:
                tag=3;
                break;
        }
    }

}