import java.util.TreeMap;

public class Three {

	private TreeMap<String, Integer> map = new TreeMap<String, Integer>();

	private void fun(String line) {
		String lineStr = line.trim();

		String[] strs = lineStr.split(" ");

		for (int i = 0; i < strs.length; i++) {
			if (!map.containsKey(strs[i])) {
				map.put(strs[i], 1);
			} else {
				int value = map.get(strs[i]) + 1;
				map.put(strs[i], value);
			}
		}

		String firstKey = map.firstKey();
		int firstValue = map.get(firstKey);

		String lastKey = map.lastKey();
		int lastValue = map.get(lastKey);

		System.out.println("最少次数的单词：" + firstKey + ", 次数：" + firstValue);
		System.out.println("最多次数的单词：" + lastKey + ", 次数：" + lastValue);

	}

}
