package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	static Pattern p = Pattern.compile("<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>");
	public static void main(String[] args) {
		outs();
	}
	
	static void outs () {
		List<String> paths = new ArrayList<String>();
		Copyer.collectFileAbsolutePaths(new File("D:\\cms\\ROOT"), paths);
		
		File file;
		for (String path : paths) {
			if (path.endsWith(".html") && path.indexOf("\\brand\\") < 0) {
				file = new File(path);
				out(file);
			}
		}
	}
	
	static void out(File file) {
		List<String> lines = readFile(file);
		
		for (String line : lines) {
			if (line != null && (line.contains("http://") || line.contains("https://")) && !line.contains("www.chinamobileltd.com") && !line.contains("10086.cn") && !line.contains("www.w3.org") && !line.contains("www.w3c.org")) {
				System.out.println("父链接：http://www.xz.10086.cn" + file.getAbsolutePath().replace("\\", "/").replace("D:/cms/ROOT", ""));
				
				Matcher m = p.matcher(line);
				while (m.find()) {
					System.out.println("外链接：" + m.group());
				}
				System.out.println();
			}
		}
	}
	
	private static List<String> readFile(File file) {
		BufferedReader br = null;
		try {
			if (file != null && file.exists() && file.isFile()) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
				
				List<String> lines = new ArrayList<String>();
				String line = null;
				while ((line = br.readLine()) != null) {
					String trimLine = line.trim();
					if (trimLine.length() > 0) {
						lines.add(trimLine);
					}
				}
				
				return lines;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
