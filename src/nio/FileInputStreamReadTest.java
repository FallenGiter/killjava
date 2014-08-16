package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileInputStreamReadTest implements IFileOperSv{
	public void read() {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(IConst.inFilePath);
			byte[] buf = new byte[IConst.bufferSize];
			
			int bytesRead;
			while((bytesRead = fin.read(buf)) > 0) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fin != null) {
					fin.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void copy() {
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream(IConst.inFilePath);
			fout = new FileOutputStream(IConst.outFilePath);
			
			byte[] buf = new byte[IConst.bufferSize];
			
			int bytesRead;
			while((bytesRead = fin.read(buf)) > 0) {
				fout.write(buf, 0, bytesRead);
				fout.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fin != null) {
					fin.close();
				}
				if (fout != null) {
					fout.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
