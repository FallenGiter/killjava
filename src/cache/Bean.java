package cache;

import org.springframework.stereotype.Service;

@Service
public class Bean {
	void getName() {
		System.out.println("Jsck");
	}
	
	public static String classToTable(String clazz) {
		if (clazz == null || clazz.trim().length() <= 0) {
			return null;
		} else {
			char[] cs = clazz.trim().toCharArray();
			String rst = "";
			char c;
			for (int i = 0; i < cs.length; i++) {
				c = cs[i];
				if (i != 0 && Character.isUpperCase(c)) {
					rst += '_';
				}
				rst += c;
			}
			return rst.toLowerCase();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(classToTable("RadioAudioTrack"));
	}
}
