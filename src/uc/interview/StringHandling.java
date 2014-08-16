package uc.interview;

public class StringHandling {
	public static void main(String[] args) {
		System.out.println(findMaxCountChar("lkjdfoeiuwr84ukfo48lkcmr34lj53i4jvo3ij45rou34cr3j4vu 3o4picqo3w4ijv5ni3j4xqkjw3lkjoi3j4vwjk"));
	}
	
	
	public static String findMaxCountChar(String str) {
		StringBuffer sb = new StringBuffer(str);
		char[] cs = str.toCharArray();
		
		String maxStr = null, tempStr;
		int maxCount = -1, temp = 0, j = 0;
		for (int i = 0; i < cs.length; i++) {
			tempStr = String.valueOf(cs[i]);
			while ((j = sb.indexOf(tempStr)) >= 0) {
				++temp;
				sb.deleteCharAt(j);
			}
			
			if (maxCount < temp) {
				maxCount = temp;
				maxStr = tempStr;
			}
			
			temp = 0;
			tempStr = null;
		}
		
		return maxStr + ": " + maxCount;
	}
	
	public static String thousandFormat(String str) {
		int i = str.indexOf('.');
		String toFormat = i > 0 ? str.substring(0, i) : str;
		String remain = i > 0 ? str.substring(i) : "";
		
		StringBuffer sb = new StringBuffer(toFormat);
		sb.reverse();
		
		for (i = 3; i < sb.length(); i += 4) {
			sb.insert(i, ',');
		}
		sb.reverse();
		
		return sb.toString() + remain;
	}
}
