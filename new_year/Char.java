package new_year;

public class Char {
	public static void main(String[] args) {
		int i = 10;
		char c = 'A';// A��ֵΪ65
		System.out.println(i + c);

		c = 'a';// a��ֵΪ97
		System.out.println(i + c);

		c = '0';// 0��ֵΪ48
		System.out.println(i + c);
		// �ַ�Ҳ�ܽ���+����
		// A-Z��a-z��0-9����
		int x = i + c;
		System.out.println(x);// �ж�����ʽ���Զ�����������ߵȼ���ͬ�ı��ʽ��byte��short��charֱ��������int����
	}

}
