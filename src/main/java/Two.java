import org.junit.Test;

public class Two {

	private static int[] bucket = new int[26];

	private static boolean isA2Z(String line) {
		String lineStr = line.toUpperCase();

		int len = line.length();
		for (int i = 0; i < len; i++) {
			char ch = lineStr.charAt(i);
			int chInt = ch - 65;
			bucket[chInt] = 1;
		}

		int count = 0;

		for (int i = 0; i < 26; i++) {
			if (bucket[i] == 1) {
				count++;
			}
		}

		return count == 26;

	}
	
	@Test
	public void testName() throws Exception {
		String testStr = "fdsajfkiuiuidsafd";
		boolean b = Two.isA2Z(testStr);
		
		System.out.println(b);
	}

}
