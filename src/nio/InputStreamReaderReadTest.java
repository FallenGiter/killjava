package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InputStreamReaderReadTest implements IFileOperSv {
	@Override
	public void read() {
		InputStreamReader inR = null;
		try {
			inR = new InputStreamReader(new FileInputStream(IConst.inFilePath));
			
			char[] buf = new char[IConst.bufferSize];
			
			int charsRead;
			while ((charsRead = inR.read(buf)) > 0) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inR != null) {
					inR.close();
				}
			} catch (Exception e) {
				
			}
		}
	}

	@Override
	public void copy() {
		InputStreamReader inR = null;
		OutputStreamWriter outW = null;
		try {
			inR = new InputStreamReader(new FileInputStream(IConst.inFilePath));
			outW = new OutputStreamWriter(new FileOutputStream(IConst.outFilePath));
			
			char[] buf = new char[IConst.bufferSize];
			
			int charsRead;
			while ((charsRead = inR.read(buf)) > 0) {
				outW.write(buf, 0, charsRead);
				outW.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inR != null) {
					inR.close();
				}
				if (outW != null) {
					outW.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
