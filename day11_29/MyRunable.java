package day11_29;

public class MyRunable implements Runnable {
	int count = 0;

	@Override
	public void run() {
		// System.out.println("×ÓÏß³Ì");
		while (count < 20) {
			System.out.println(Thread.currentThread().getName() + "*****************" + count);
			count++;
		}

	}
}
