package spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("spring")
@Scope("prototype")
public class Bean {
	void getName() {
		System.out.println("中".getBytes().length);
	}
	
	public static List<Integer> randomInt(int a, int b, int n) {
		if (n <= 0 || n > (b - a + 1)) {
			return null;
		} else {
			int size = b - a + 1;
			List<Integer> seeds = new ArrayList<Integer>(size);
			for (int i = a; i <= b; i++) {
				seeds.add(i);
			}
			
			List<Integer> rsts = new ArrayList<Integer>(n);
			int random;
			while (rsts.size() != n) {
				random = (int)(Math.random() * seeds.size());
				
				rsts.add(seeds.get(random));
				seeds.remove(random);
			}
			
			return rsts;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("中".getBytes().length);
	}
}
