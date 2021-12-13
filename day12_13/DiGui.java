package day12_13;

import java.util.ArrayList;

public class DiGui {

	private static String[] data1;
	public static int res1 = -1;

	public static void main(String[] args) {

		int[] num = { 5, 3, 7, 9, 1 };
		getFuHao(num);

		String[] s = fuHao();
		System.out.println(s);
		System.out.println(data1);
	}

	public static void getFuHao(int[] arr) {

		String[] allArr = new String[] { arr[0] + "", "", arr[1] + "", "", arr[2] + "", "", arr[3] + "", "",
				arr[4] + "" };

		for (int i = 0; i < allArr.length; i++) {
			if (i % 2 != 0) {
				int rand = suanFa();
				switch (rand) {
				case 1:
					allArr[i] = "+";
					break;
				case 2:
					allArr[i] = "-";
					break;
				case 3:
					allArr[i] = "*";
					break;
				case 4:
					allArr[i] = "¡Â";
					break;
				}
			}
		}

		data1 = allArr;

	}

	public static int getNextNum(ArrayList<Integer> usedNum, int index) {
		int[] canUsedNums = {};
		int nextIndex = -1;
		if (index % 2 == 0) {
			switch (index) {
			case 0:
				canUsedNums = new int[] { 1, 3 };
				break;
			case 2:
				canUsedNums = new int[] { 1, 5 };
				break;
			case 4:
				canUsedNums = new int[] { 1, 3, 5, 7 };
				break;
			case 6:
				canUsedNums = new int[] { 3, 7 };
				break;
			case 8:
				canUsedNums = new int[] { 5, 7 };
				break;

			}

		} else {
			switch (index) {
			case 1:
				canUsedNums = new int[] { 0, 2, 4 };
				break;
			case 3:
				canUsedNums = new int[] { 0, 4, 6 };
				break;
			case 5:
				canUsedNums = new int[] { 2, 4, 8 };
				break;
			case 7:
				canUsedNums = new int[] { 4, 6, 8 };
				break;

			}
		}

		if (usedNum.size() < 2) {
			nextIndex = canUsedNums[getRandInex(canUsedNums.length)];
		} else {
			int[] canUsedNums2 = canUsedNums.clone();
			for (int i = 0; i < canUsedNums2.length; i++) {
				for (int j = 0; j < usedNum.size(); j++) {
					if (canUsedNums2[i] == usedNum.get(j)) {

						canUsedNums = removeArrayByValue(canUsedNums, canUsedNums2[i]);
					}
				}
			}

			nextIndex = canUsedNums[getRandInex(canUsedNums.length)];
		}

		return nextIndex;
	}

	public static int get2NumS(int[] arr) {
		ArrayList<Integer> usedNum = new ArrayList<>();

		String[] allArr = data1;

		int index = (int) (Math.random() * 5) * 2;
		usedNum.add(index);
		int index2 = getNextNum(usedNum, index);
		usedNum.add(index2);
		int index3 = getNextNum(usedNum, index2);

		int result = 0;

		if (allArr[index2].equals("-")) {
			result = Math.abs(Integer.parseInt(allArr[index]) - Integer.parseInt(allArr[index3]));
		} else if (allArr[index2].equals("+")) {
			result = Math.abs(Integer.parseInt(allArr[index]) + Integer.parseInt(allArr[index3]));
		} else if (allArr[index2].equals("*")) {
			result = Math.abs(Integer.parseInt(allArr[index]) * Integer.parseInt(allArr[index3]));
		} else {
			result = Math.abs(Integer.parseInt(allArr[index]) / Integer.parseInt(allArr[index3]));
		}

		return result;
	}

	public static int get3NumS(int[] arr) {
		ArrayList<Integer> usedNum = new ArrayList<>();

		int index = (int) (Math.random() * 5) * 2;
		usedNum.add(index);
		int index2 = getNextNum(usedNum, index);
		usedNum.add(index2);
		int index3 = getNextNum(usedNum, index2);
		usedNum.add(index3);
		int index4 = getNextNum(usedNum, index3);
		usedNum.add(index4);
		int index5 = getNextNum(usedNum, index4);

		String data2 = "" + data1[index] + data1[index2] + data1[index3] + data1[index4] + data1[index5];
		getResult(data2, 0, 0);

		return res1;
	}

	public static int get4NumS(int[] arr) {
		ArrayList<Integer> usedNum = new ArrayList<>();

		int index = (int) (Math.random() * 5) * 2;
		usedNum.add(index);
		int index2 = getNextNum(usedNum, index);
		usedNum.add(index2);
		int index3 = getNextNum(usedNum, index2);
		usedNum.add(index3);
		int index4 = getNextNum(usedNum, index3);
		usedNum.add(index4);
		int index5 = getNextNum(usedNum, index4);
		usedNum.add(index5);
		int index6 = getNextNum(usedNum, index5);
		usedNum.add(index6);
		int index7 = getNextNum(usedNum, index6);

		String data2 = "" + data1[index] + data1[index2] + data1[index3] + data1[index4] + data1[index5] + data1[index6]
				+ data1[index7];
		getResult(data2, 0, 0);

		return res1;
	}

	private static int[] remveArrayByIndex(int[] arr, int index) {
		ArrayList<Integer> l = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (i == index) {
				continue;
			}
			l.add(arr[i]);
		}

		int[] arr1 = new int[l.size()];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = l.get(i);
		}
		return arr1;
	}

	private static int[] removeArrayByValue(int[] arr, int v) {
		int j = -1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == v) {
				j = i;
			}
		}
		if (j == -1) {
			return arr;
		} else {
			return remveArrayByIndex(arr, j);
		}

	}

	private static int getRandInex(int n) {
		return (int) Math.random() * n;
	}

	public static void getResult(String data, int count, int res) {

		char[] array = data.toCharArray();
		if (count == 0) {

			if (array[count + 1] == '-') {
				res = Math.abs(Integer.parseInt(String.valueOf(array[count]))
						- Integer.parseInt(String.valueOf(array[count + 2])));
			} else if (array[count + 1] == '+') {
				res = Math.abs(Integer.parseInt(String.valueOf(array[count]))
						+ Integer.parseInt(String.valueOf(array[count + 2])));
			} else if (array[count + 1] == '*') {
				res = Math.abs(Integer.parseInt(String.valueOf(array[count]))
						* Integer.parseInt(String.valueOf(array[count + 2])));
			} else {
				res = Math.abs(Integer.parseInt(String.valueOf(array[count]))
						/ Integer.parseInt(String.valueOf(array[count + 2])));
			}

			count += 2;

		} else {

			if (array[count + 1] == '-') {
				res = res - Integer.parseInt(String.valueOf(array[count + 2]));
			} else if (array[count + 1] == '+') {
				res = res + Integer.parseInt(String.valueOf(array[count + 2]));
			} else if (array[count + 1] == '*') {
				res = res * Integer.parseInt(String.valueOf(array[count + 2]));
			} else {
				res = res / Integer.parseInt(String.valueOf(array[count + 2]));
			}

			count += 2;
		}

		if (count < data.length() - 1) {
			getResult(data, count, res);

		} else {
			res1 = res;
		}
	}

	public static int suanFa() {
		int rand;
		rand = (int) (Math.random() * 4 + 1);
		return rand;
	}

	public static String[] fuHao() {
		return data1;
	}
}
