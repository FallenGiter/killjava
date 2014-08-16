package nio;

import java.io.FileReader;
import java.io.FileWriter;

public class FileReaderReadTest implements IFileOperSv {
	@Override
	public void read() {
		FileReader fr = null;
		
		try {
			fr = new FileReader(IConst.inFilePath);
			
			char[] buf = new char[IConst.bufferSize];
			
			int charsRead;
			while ((charsRead = fr.read(buf)) > 0) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void copy() {
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader(IConst.inFilePath);
			fw = new FileWriter(IConst.outFilePath);
			
			char[] buf = new char[IConst.bufferSize];
			
			int charsRead;
			while ((charsRead = fr.read(buf)) > 0) {
				fw.write(buf, 0, charsRead);
				fw.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
