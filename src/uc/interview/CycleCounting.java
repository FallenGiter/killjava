package uc.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CycleCounting {

	public static void main(String[] args) {
		cycleCounting(Arrays.asList(new Integer[]{1,2,3,4,5,6}), 40);
		System.out.println("================");
		cycleCounting(new String[]{"1","2","3","4","5","6"}, 40);
	}

	static void cycleCounting(List<Integer> monkeys, int triggerNum) {
		if (monkeys != null) {
			int size = monkeys.size();
			if (size == 1) {
				System.out.println("Monkey " + monkeys.get(0) + " is out.");
			} else {
				int startNum = 0, monkey, counter = 0;
				List<Integer> tmp;
				while(monkeys.size() > 0) {
					if (startNum > monkeys.size() - 1) {
						startNum = 0;
					}
					
					monkey = monkeys.get(startNum);
					if (++counter % triggerNum == 0) {
						tmp = new ArrayList<Integer>(monkeys);
						tmp.remove(startNum);
						monkeys = tmp;
						
						System.out.println("Monkey " + monkey + " is out.");
						counter = 0;
					} else {
						++startNum;
					}
				}
			}
		}
	}
	
	static void cycleCounting(String[] monkeys, int triggerNum) {
		if (monkeys != null) {
			int len = monkeys.length;
			if (len == 1) {
				System.out.println("Monkey " + monkeys[0] + " is out.");
				monkeys[0] = null;
			} else {
				String monkey;
				int startNum = 0, counter = 0, nullCount = len;
				while(nullCount > 0) {
					if (startNum > len - 1) {
						startNum = 0;
					}
					
					monkey = monkeys[startNum];
					if (monkey != null && ++counter % triggerNum == 0) {
						System.out.println("Monkey " + monkey + " is out.");
						counter = 0;
						monkeys[startNum] = null;
						--nullCount;
					} else {
						++startNum;
					}
				}
			}
		}
	}
}
