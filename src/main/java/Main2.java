import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		int res = 1;
		for (int i = 0; i < n; i++) {
			int number = sc.nextInt();
			res = foo(number);
			System.out.println(res);
		}

		sc.close();
	}

	private static int bar(int n, int m) {
		if (m == 1)
			return 1;

		if (m > n)
			return 0;

		return m * (bar(n - 1, m - 1) + bar(n - 1, m));
	}

	private static int foo(int n) {
		int s = 0;
		for (int i = 1; i <= n; i++) {
			s += bar(n, i);
		}
		return s;
	}

}
