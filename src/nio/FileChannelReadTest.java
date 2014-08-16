package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelReadTest implements IFileOperSv {
	@Override
	public void read() {
		FileInputStream fis = null;
		FileChannel fInChl = null;
		try {
			fis = new FileInputStream(IConst.inFilePath);
			fInChl = fis.getChannel();
			
			ByteBuffer bb = ByteBuffer.allocate(IConst.bufferSize);
			
			int read;
			while ((read = fInChl.read(bb)) > 0) {
				bb.flip();
				bb.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fInChl != null) {
					fInChl.close();
				}
				
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void copy() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel fInChl = null, fOutChl = null;
		try {
			fis = new FileInputStream(IConst.inFilePath);
			fos = new FileOutputStream(IConst.outFilePath);
			fInChl = fis.getChannel();
			fOutChl = fos.getChannel();
			
			ByteBuffer bb = ByteBuffer.allocate(IConst.bufferSize);
			
			int read;
			while ((read = fInChl.read(bb)) > 0) {
				bb.flip();
				fOutChl.write(bb);
				bb.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fInChl != null) {
					fInChl.close();
				}
				
				if (fOutChl != null) {
					fOutChl.close();
				}
				
				if (fis != null) {
					fis.close();
				}
				
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
