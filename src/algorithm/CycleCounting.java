package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CycleCounting {

	public static void main(String[] args) {
		cycleCounting(Arrays.asList(new Integer[]{1,2,3,4,5,6}), 10);
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
//					System.out.println("Monkey " + monkey + " is encountered");
					
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
}
