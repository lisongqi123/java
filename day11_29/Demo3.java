package day11_29;

public class Demo3 {
	public static void main(String[] args) {
		// 1
		Mythread mythread = new Mythread();
		mythread.start();
		Mythread mythread1 = new Mythread();
		mythread1.start();
		// 2
		MyRunable able = new MyRunable();
		Thread thread = new Thread(able);
		thread.start();
		Thread thread1 = new Thread(able);
		thread1.start();
		// 3
		Thread thread2 = new Thread("线程x") {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		};
		thread2.start();
		System.out.println(Thread.currentThread().getName());
		// 4
		Thread thread3 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName());
			}
		}, "线程y");
		thread3.start();
	}
}
