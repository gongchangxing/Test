package com.dhcc.Array;

// ~ File Information
// ====================================================================================================================

public class BubbleSortTest {

	public static void main(String[] args) {
		int [] in = new int[]{4,1,8,5,10};
		BubbleSort(in);
	}

	public static void BubbleSort(int[] input) {
		int temp = 0;
		for (int i = 0; i < input.length - 1; i++) {

			for (int j = 0; j < input.length - i - 1; j++) {
				if (input[j] > input[j + 1]) {
					temp = input[j];
					input[j] = input[j + 1];
					input[j + 1] = temp;

				}
			}
			
		}
		
		System.out.println("------------------");
		for (int k = 0; k < input.length; k++) {
			System.out.println(input[k]);
		}

	}
}
