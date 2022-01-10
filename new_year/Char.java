package new_year;

public class Char {
	public static void main(String[] args) {
		int i = 10;
		char c = 'A';// A的值为65
		System.out.println(i + c);

		c = 'a';// a的值为97
		System.out.println(i + c);

		c = '0';// 0的值为48
		System.out.println(i + c);
		// 字符也能进行+操作
		// A-Z；a-z；0-9连续
		int x = i + c;
		System.out.println(x);// 有多个表达式会自动提升到和最高等级相同的表达式，byte，short，char直接提升到int类型
	}

}
