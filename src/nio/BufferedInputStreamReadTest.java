package nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferedInputStreamReadTest implements IFileOperSv{
	@Override
	public void read() {
		BufferedInputStream bin = null;
		
		try {
			bin = new BufferedInputStream(new FileInputStream(IConst.inFilePath));
			
			byte[] buf = new byte[IConst.bufferSize];
			
			int bytesRead;
			while ((bytesRead = bin.read(buf)) > 0) {
			
			}
		} catch (Exception e) {
			
		} finally {
			try {
				if (bin != null) {
					bin.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void copy() {
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			bin = new BufferedInputStream(new FileInputStream(IConst.inFilePath));
			bout = new BufferedOutputStream(new FileOutputStream(IConst.outFilePath));
			
			byte[] buf = new byte[IConst.bufferSize];
			
			int bytesRead;
			while((bytesRead = bin.read(buf)) > 0) {
				bout.write(buf, 0, bytesRead);
				bout.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bin != null) {
					bin.close();
				}
				if (bout != null) {
					bout.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
