package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Copyer {
	final static String FROM = "D:\\crm\\rm\\";        		     // 要拷贝的根目录， 如：开发分支根目录
	final static String TO = "D:\\crm\\jx\\";                 		 // 要拷贝到的根目录， 如：基线分支根目录
	final static String URI_LIST = "D:\\filelist.txt";      		 // 代码路径列表(可以是文件或者文件夹)，一个路径一行， 如：src\com\asiainfo\boss\res\stockalarm\dao\impl\StockAlarmConfigDAOImpl.java
	final static String ENCODING = "GBK";                   		 // 文件编码， 如：GBK
	final static int BUFFER_SIZE = 1024;                    		 // 读写缓存大小
	final static String[] EXCLUDE_DIRS = {".svn"};         			 // 需要排除的目录， 如.svn
	final static String[] EXCLUDE_FILE_SUFFIXS = {"tar", "txt"};     // 需要排除的文件的后缀，如 txt
	final static boolean IS_SHOW_WARNNING = true;            		 // 是否输出警告信息
	final static boolean IS_SHOW_INFO = true;               		 // 是否输出一般信息
	static long fileCopied;
	
	public static void main(String[] args) throws Exception {
		Copyer.copyDirOrFile();
//		Copyer.copyDirFileToDir("D:\\svncrm\\rmcrm\\lib", "D:\\svncrm\\rmcrm\\html\\WEB-INF\\lib");
	}

	public static void copyDirFileToDir(String fromDir, String toDir) throws IOException {
		if (fromDir == null || fromDir.trim().length() <= 0) {
			Copyer.warn("From dir can not be null or blank!");
			return;
		}
		if (toDir == null || toDir.trim().length() <= 0) {
			Copyer.warn("To dir can not be null  or blank!");
			return;
		}
		fromDir = Copyer.fixPath(fromDir.trim());
		toDir = Copyer.fixPath(toDir.trim());
		File fromFile = new File(fromDir), toFile = new File(toDir);
		if (!fromFile.exists()) {
			Copyer.warn("From dir does not exist !");
			return;
		}
		if (toFile.getParentFile() != null) {
			if (!toFile.getParentFile().exists()) {
				String destPath = toFile.getParentFile().getAbsolutePath();
				Copyer.info("To dir doesn't exist, trying to create: " + destPath);
				if (!toFile.getParentFile().mkdirs()) {
					Copyer.warn("To dir's parent directory cannot be created: " + destPath);
					return;
				} else {
					Copyer.info("To dir's parent directory created successfully: " + destPath);
				}
			}
	    }
		
		List<String> paths = new ArrayList<String>();
		Copyer.collectFileAbsolutePaths(fromFile, paths);
		String fileName, toPath;
		for(String path : paths) {
			fileName = Copyer.getFileName(path);
			if (toDir.endsWith(File.separator)) {
				toPath = Copyer.fixPath(toDir + fileName);
			} else {
				toPath = Copyer.fixPath(toDir + File.separator + fileName);
			}
			Copyer.copyFile(new File(path), new File(toPath));
		}
		Copyer.info(">>>>>>>>>>>> " + fileCopied + " files had been copied !");
	}
	
	public static void copyDirOrFile() throws IOException {
		File uriList = new File(URI_LIST);
		if (!uriList.exists() || (uriList.exists() && uriList.isDirectory())) {
			Copyer.warn("File list does not exist or is not a file: " + URI_LIST);
		} else {
			List<String> uris = Copyer.readFile(uriList);
			if (uris == null || uris.size() <= 0) {
				Copyer.warn("No content in file: " + uriList.getAbsolutePath());
			} else {
				String uri;
				final int size = uris.size();
				File fromFile, toFile;
				final List<String> subFilePaths = new ArrayList<String>();
				for (int i = 0; i < size; i++) {
					uri = uris.get(i);
					if (uri != null && uri.length() > 0) {
						fromFile = new File(Copyer.fixPath(FROM + uri));
						if (!fromFile.exists()) {
							Copyer.warn("File does not exsit, will be ignored: " + fromFile.getAbsolutePath());
						} else {
							if (fromFile.isFile()) {
								if (!Copyer.isFileExclude(fromFile)) {
									toFile = new File(Copyer.fixPath(TO + uri));
									Copyer.copyFile(fromFile, toFile);
								}
							} else if (fromFile.isDirectory()) {
								if (!Copyer.isDirExclude(fromFile)) {
									Copyer.collectFileAbsolutePaths(fromFile, subFilePaths);
									String subFilePath;
									for (int j = 0; j < subFilePaths.size(); j++) {
										subFilePath = Copyer.fixPath(subFilePaths.get(j));
										fromFile = new File(subFilePath);
										if (!Copyer.isFileExclude(fromFile)) {
											toFile = new File(TO + subFilePath.replace(FROM, ""));
											Copyer.copyFile(fromFile, toFile);
										}
									}
									subFilePaths.clear();
								}
							} else {
								Copyer.warn("File is not a file or a directory ! Path: " + fromFile.getAbsolutePath());
							}
						}
					} else {
						Copyer.info("Empty or blank path is ignored !");
					}
				}
				Copyer.info(">>>>>>>>>>>> " + fileCopied + " files had been copied !");
			}
		}
	}

	public static void copyFile(File fileToBeCopied, File destination) throws IOException {
		if (fileToBeCopied == null) {
			Copyer.warn("The file to be copied is null !");
			return;
		}
		String srcPath = fileToBeCopied.getAbsolutePath();
		if (!fileToBeCopied.exists()) {
			Copyer.warn("The file to be copied doesn't exist: " + srcPath);
			return;
		}
		if (fileToBeCopied.exists() && !fileToBeCopied.isFile()) {
			Copyer.warn("The file to be copied is not a file: " + srcPath);
			return;
		}
		if (destination == null) {
			Copyer.warn("Destination file is null !");
			return;
		}
		if (fileToBeCopied.getCanonicalPath().equals(destination.getCanonicalPath())) {
			Copyer.warn("The file to be copy and destination file are the same: " + destination.getAbsolutePath());
			return;
		}
		if (destination.getParentFile() != null) {
			if (!destination.getParentFile().exists()) {
				String destPath = destination.getParentFile().getAbsolutePath();
				Copyer.info("Directory doesn't exist, trying to create: " + destPath);
				if (!destination.getParentFile().mkdirs()) {
					Copyer.warn("Destination file's parent directory cannot be created: " + destPath);
					return;
				} else {
					Copyer.info("Destination file's parent directory created successfully: " + destPath);
				}
			}
	    }
		String destPath = destination.getAbsolutePath();
		if (destination.exists()) {
			if (!destination.canWrite()) {
				Copyer.warn("Destination file exists but is read-only: " + destPath);
				return;
			}
			Copyer.info("File exsit, will be overwrote: " + destPath);
		} else {
			Copyer.info("File does not exsit, will be created: " + destPath);
		}
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		final byte[] buffer = new byte[BUFFER_SIZE];
		try {
			bin = new BufferedInputStream(new FileInputStream(fileToBeCopied), BUFFER_SIZE);
			bout = new BufferedOutputStream(new FileOutputStream(destination), BUFFER_SIZE);
			int n;
			while((n = bin.read(buffer)) != -1) {
				bout.write(buffer, 0, n);
				bout.flush();
			}
			++fileCopied;
		} catch (IOException e) {
			throw e;
		} finally {
			if (bin != null) {
				bin.close();
			}
			if (bout != null) {
				bout.close();
			}
		}
	}
	
	public static void collectFileAbsolutePaths(File file, List<String> paths) {
		if (file == null) {
			Copyer.warn("Input file is null !");
			return;
		}
		if (!file.exists()) {
			Copyer.warn("Input file doesn't exist !");
			return;
		}
		if (paths == null) {
			Copyer.warn("File path container can not be null !");
			return;
		}
		
		if (file.isDirectory()) {
			if (!Copyer.isDirExclude(file)) {
				File[] subFiles = file.listFiles();
				if (subFiles != null && subFiles.length > 0) {
					for (int i = 0; i < subFiles.length; i++) {
						collectFileAbsolutePaths(subFiles[i], paths);
					}
				}
			}
		} else if (file.isFile()) {
			if (!Copyer.isFileExclude(file)) {
				paths.add(file.getAbsolutePath());
			}
		} else {
			Copyer.warn("Input file is not a file or a directory ! Path: " + file.getAbsolutePath());
		} 
	}
	
	private static List<String> readFile(File file) throws IOException {
		BufferedReader br = null;
		try {
			if (file != null && file.exists() && file.isFile()) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), ENCODING));
				
				List<String> lines = new ArrayList<String>();
				String line = null;
				while ((line = br.readLine()) != null) {
					String trimLine = line.trim();
					if (trimLine.length() > 0) {
						lines.add(trimLine);
					}
				}
				
				return lines;
			} else {
				Copyer.warn("File is null or file does not exist or file is not a file .");
			}
		} catch (IOException e) {
			throw e;
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
	
	public static void info(String msg) {
		if (IS_SHOW_INFO) {
			System.out.println(Copyer.class.getName() + " INFO: " + msg);
		}
	}
	
	public static void warn(String msg) {
		if (IS_SHOW_WARNNING) {
			System.out.println(Copyer.class.getName() + " WARN: " + msg);
		}
	}
	
	private static boolean isDirExclude(File dir) {
		if (dir != null) {
			for (int i = 0; i < EXCLUDE_DIRS.length; i++) {
				if (EXCLUDE_DIRS[i].equals(dir.getName())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean isFileExclude(File file) {
		if (file != null) {
			for (int i = 0; i < EXCLUDE_FILE_SUFFIXS.length; i++) {
				if (EXCLUDE_FILE_SUFFIXS[i].equals(Copyer.getFileSuffix(file))) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static String getFileName(String path) {
		if (path == null || path.trim().length() <= 0) {
			Copyer.warn("Path can not be null or blank!");
			return path;
		} else {
			return path.substring(path.trim().lastIndexOf(File.separatorChar) + 1);
		}
	}
	
	public static String getFileSuffix(File file) {
		if (file == null) {
			Copyer.info("Input file is null !");
			return null;
		}
		if (!file.isFile()) {
			Copyer.info("Input file is not a file !");
			return null;
		}
		
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf('.') + 1);
	}
	
	public static String fixPath(String path) {
		String tmp = path;
		if (tmp != null && tmp.trim().length() > 0) {
			StringBuffer sb = new StringBuffer();
			char[] charArr = tmp.toCharArray();
			char c;
			int len;
			for (int i = 0; i < charArr.length; i++) {
				c = charArr[i];
				if (c == '/' || c == '\\') {
					len = sb.length();
					if (!((len - 1) == sb.lastIndexOf(File.separator))) {
						sb.append(File.separator);
					}
				} else {
					sb.append(c);
				}
			}
			
			return sb.toString();
		}
		
		return path;
	}
}
