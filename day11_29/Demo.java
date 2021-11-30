package day11_29;

public class Demo extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		for (int i = 10; i < 20; i++) {
			Demo demo = new Demo();
			demo.start();
		}
	}

}
