import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		long sec = 0L;
		for (int i = 0; i < n; i++) {
			sec = sc.nextLong();

			Times times = new Times();
			times.addSec(sec);
			System.out.println(times);
		}

		sc.close();

	}

}

class Times {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int min;
	private int sec;

	public Times() {
		super();
		this.year = 1970;
		this.month = 1;
		this.day = 1;
		this.hour = 0;
		this.min = 0;
		this.sec = 0;
	}

	public void addSec(long s) {

		// 分钟进位
		while (s >= 60) {
			addMin(1);
			s = s - 60;
		}

		this.sec = (int) s;
	}

	/**
	 * @param i
	 */
	private void addMin(int i) {
		this.min += i;
		if (this.min >= 60) {
			addHour(1);
			this.min = this.min - 60;
		}
	}

	/**
	 * @param i
	 */
	private void addHour(int i) {
		this.hour += i;
		if (this.hour >= 24) {
			addDay(1);
			this.hour = this.hour - 24;
		}
	}

	/**
	 * @param i
	 */
	private void addDay(int i) {
		this.day += i;
		if (this.day >= 30) {
			addMonth(1);
			this.day = this.day - 30;
		}

	}

	/**
	 * @param i
	 */
	private void addMonth(int i) {
		this.month += i;
		if (this.month >= 12) {
			addYear(1);
			this.month = this.month - 12;
		}

	}

	@Override
	public String toString() {
		String str = year + "/";
		if (month < 10) {
			str += "0";
		}
		str += month + "/";

		if (day < 10) {
			str += "0";

		}
		str += day + " ";

		if (hour < 10) {
			str += "0";
		}
		str += hour + ":";

		if (min < 10) {
			str += "0";
		}
		str += min + ":";
		if (sec < 10) {
			str += "0";
		}
		str += sec;
		return str;
	}

	/**
	 * @param i
	 */
	private void addYear(int i) {
		this.year += i;
	}

}
