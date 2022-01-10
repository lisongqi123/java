package new_year;

public class Change_Type {
	public static void main(String[] args) {
		double a = 10;
		System.out.println(a);

		byte b = 10;
		System.out.println(b);
		short c = b;
		System.out.println(c);
		int d = c;
		System.out.println(d);
//		char e = b;byte不能转为char，但char能转为int及后面的long，float，double；
		// 强转（类型大的转为类型小的）
//		int x = 88.88;
		int x = (int) 88.88;
		System.out.println(x);// 强转后可能会有数值的丢失

		System.out.println("asd" + "asd");
		System.out.println("asd" + 123);

		System.out.println("asd" + 1 + 2);
		System.out.println(2 + 1 + "asd");
	}

}
