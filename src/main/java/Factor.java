public class Factor {

	public static void main(String[] args) {
		int count = 0;
		for (int i = 1; i <= 2015; i++) {
			if (getFactorNum(i) % 2 == 1) {
				count++;

			}
		}

		System.out.println(count);
	}

	public static int getFactorNum(int n) {
		if (n == 1) {
			return 1;
		}

		int end = n / 2;
		int count = 0;

		for (int i = 1; i <= end; i++) {
			if (n % i == 0) {
				count++;
			}
		}

		return ++count;
	}

}
