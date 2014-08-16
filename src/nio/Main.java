package nio;

public class Main {
	static String[] impls = {
			FileInputStreamReadTest.class.getName(),
			FileChannelReadTest.class.getName(),
			BufferedInputStreamReadTest.class.getName(),
			FileReaderReadTest.class.getName(),
			InputStreamReaderReadTest.class.getName()};
	
	public static void main(String[] args) throws Exception {
		String impl = impls[0]; 
		IFileOperSv sv = (IFileOperSv) Class.forName(impl).newInstance();
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < IConst.repeat; i++) {
			sv.copy();
		}
		
		System.out.println(impl + "平均耗时：" + (double)(System.currentTimeMillis() - start) / (double)IConst.repeat + "毫秒.");
	}
}
