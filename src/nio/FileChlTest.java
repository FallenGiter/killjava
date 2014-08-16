package nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileChlTest {
	static int buffer = 4096, repeat = 1;
	
	public static void main(String[] args) {
		FileChlTest ft = new FileChlTest();
		File srcFile = new File("D:\\TDDOWNLOAD\\adt-bundle-windows-x86.zip"), destFile = new File("D:\\adt-bundle-windows-x86.zip"), nioDestFile = new File("D:\\adt-bundle-windows-x86-nio.zip");
		
//		NioFileMover fileMover = ft.new NioFileMover(srcFile, nioDestFile);
//		fileMover.start();
		
		FileMover fm = ft.new FileMover(srcFile, destFile);
		fm.start();
		
//		FileMapper fMapper = ft.new FileMapper(srcFile);
//		fMapper.mapFileReadOnly();
//		fMapper.mapFileReadWrite();
	}
	
	class FileMapper {
		private File srcFile;
		
		public FileMapper(File srcFile) {
			this.srcFile = srcFile;
		}
		
		public void mapFileReadOnly() {
			FileInputStream fis = null;
			FileChannel fChl = null;
			
			try {
				fis = new FileInputStream(this.srcFile);
				fChl = fis.getChannel();
				
				MappedByteBuffer bb = fChl.map(MapMode.READ_ONLY, 0, 5);
				byte[] con = new byte[bb.capacity()];
				
				bb.get(con);
				
				log(new String(con));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fChl != null) {
						fChl.close();
					}
					if (fis != null) {
						fis.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void mapFileReadWrite() {
			RandomAccessFile raf = null;
			FileChannel fChl = null;
			
			try {
				raf = new RandomAccessFile(this.srcFile, "rw" );
				fChl = raf.getChannel();
				
				MappedByteBuffer bb = fChl.map(MapMode.READ_WRITE, 0, 100);
				
				bb.putDouble(20, 54.3);
				log("" + bb.getDouble(20));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fChl != null) {
						fChl.close();
					}
					if (raf != null) {
						raf.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class FileMover extends Thread {
		private File srcFile, destFile;
		
		public FileMover(File srcFile, File destFile) {
			this.srcFile = srcFile;
			this.destFile = destFile;
		}
		
		@Override
		public void run() {
			int total = 0;
			for (int i = 0; i < repeat; i++) {
				total += move();
			}
			log("普通IO移动文件" + this.srcFile.getAbsolutePath() + "\n平均耗时：" + (double)total/(double)repeat + "毫秒.");
		}
		
		private long move() {
			long start = System.currentTimeMillis();
			
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(this.srcFile));
				bos = new BufferedOutputStream(new FileOutputStream(this.destFile));
				
				byte[] bb = new byte[buffer];
				
				int total = bis.available(), totalRead = 0;
				
				int read;
				while ((read = bis.read(bb)) > 0) {
					bos.write(bb, 0, read);
					bos.flush();
					totalRead += read;
					log("普通IO移动文件" + this.srcFile.getAbsolutePath() + "\n已完成：" + ((double)totalRead * (double)100/(double)total) + "/%");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (bis != null) {
						bis.close();
					}
					
					if (bos != null) {
						bos.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return System.currentTimeMillis() - start;
		}
	}

	class NioFileMover extends Thread {
		private File srcFile, destFile;
		
		public NioFileMover(File srcFile, File destFile) {
			this.srcFile = srcFile;
			this.destFile = destFile;
		}
		
		@Override
		public void run() {
			int total = 0;
			for (int i = 0; i < repeat; i++) {
				total += move();
			}
			log("新IO移动文件" + this.srcFile.getAbsolutePath() + "\n平均耗时：" + (double)total / (double)repeat + "毫秒.");
		}
		
		private long move() {
			long start = System.currentTimeMillis();
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			FileChannel fInChl = null, fOutChl = null;
			try {
				fis = new FileInputStream(this.srcFile);
				fos = new FileOutputStream(this.destFile);
				fInChl = fis.getChannel();
				fOutChl = fos.getChannel();
				
				ByteBuffer bb = ByteBuffer.allocate(buffer);
				
				int total = fis.available(), totalRead = 0;
				
				int read;
				while ((read = fInChl.read(bb)) > 0) {
					bb.flip();
					fOutChl.write(bb);
					bb.clear();
					totalRead += read;
					log("新IO移动文件" + this.srcFile.getAbsolutePath() + "\n已完成：" + ((double)totalRead * (double)100/(double)total) + "/%");
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
			
			return System.currentTimeMillis() - start;
		}
	}
	
	void log(String logMsg) {
		System.out.println(logMsg);
	}
}



