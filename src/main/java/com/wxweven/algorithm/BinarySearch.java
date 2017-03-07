package com.wxweven.algorithm;

/**
 * @author wxweven
 * @date 2015骞�9鏈�30鏃�
 * @version 1.0
 * @email wxweven@.com
 * @blog http://wxweven.win/
 * @Copyright: Copyright (c) wxweven 2014
 * @Description: 浜屽垎鏌ユ壘绠楁硶锛氭帓濂藉簭鎵嶈兘鐢ㄤ簩鍒嗘煡鎵�
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = { 10, 55, 69, 78, 85 };
		int x = 10;
		int pos = binarySearch(arr, 0, arr.length - 1, x);
		System.out.println(pos);
	}

	static int binarySearch(int[] arr, int start, int end, int value) {
		while (start <= end) {
			int middle = (start + end) / 2;
			int temp = arr[middle];

			if (value < temp) {
				end = middle - 1;
			} else if (value > temp) {
				start = middle + 1;
			} else {
				return middle;
			}
		}

		return -1;
	}
}
