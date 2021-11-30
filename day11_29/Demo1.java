package day11_29;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;

public class Demo1 extends Frame implements Runnable {
	public Demo1(String string) {
		// TODO Auto-generated constructor stub
	}

	int count = 60;
	boolean tag = true;
	static Button button = new Button();
	static Demo1 demo1 = new Demo1("倒计时按钮");

	@Override
	public void run() {
		while (tag) {
			if (count < 0) {
				tag = false;
			}
			count--;
			button.setLabel(count + "");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		demo1.setBackground(Color.BLUE);
		demo1.setSize(300, 300);
		button.setLabel("60");// 设置按钮
		demo1.add(button);
		demo1.setVisible(true);// 显示窗体
		Thread thread = new Thread(demo1);
		thread.start();
		demo1.addWindowListener(new WindowAdapter() {// 关闭界面
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}

}
