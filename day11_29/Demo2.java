package day11_29;

public class Demo2 implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());

	}

	public static void main(String[] args) throws InterruptedException {
		Demo2 threadx = new Demo2();
		Thread thread = new Thread(threadx, "线程x");
		Thread thread1 = new Thread(threadx, "线程y");
		thread.start();
		thread1.run();

		Thread.sleep(400);
		System.out.println(Thread.currentThread().getName());
	}

}
