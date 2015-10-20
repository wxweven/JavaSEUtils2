import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		while (sc.hasNextLine()) {

			n = Integer.valueOf(sc.nextLine());
			if (n != 0) {
				int[] cow = new int[n];

				for (int i = 0; i < n; i++) {
					if (i < 4) {
						cow[i] = 1;
					} else {
						cow[i] = cow[i - 1] + cow[i - 4];
					}
				}

				int sum = 0;
				for (int i = 0; i < n; i++) {
					sum += cow[i];
				}

				System.out.println(sum);
			} else {
				break;
			}
		}
		sc.close();
	}
}
