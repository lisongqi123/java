package day12_7;

public class DiGui {
	public static void main(String[] args) {
		show();
	}

	static int sum = 0;
	static int count = 0;
	static int end = 100;

	private static void show() {
		count++;
		sum += count;
		if (count < 100) {
			show();
		} else {
			System.out.println(sum);
		}
	}

}
