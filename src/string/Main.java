package string;

import java.io.File;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer("asdf asdf asdfasdf asdfasdfwetyhef FSDLFKLGJERKM ADSFLKJAERTJ lo");
		
		while(st.hasMoreElements()) {
			System.out.println(st.nextElement());
		}
		
		
		StreamTokenizer st1 = new StreamTokenizer(new FileReader(new File("d:\\ics_error.log")));
		
		st1.ordinaryChar('\'');
		st1.ordinaryChar('/');
		st1.ordinaryChar('\"');
		st1.ordinaryChar('.');
		st1.ordinaryChar(' ');
		
		while(st1.nextToken() != StreamTokenizer.TT_EOF) {
			switch (st1.ttype) {
				case StreamTokenizer.TT_NUMBER: {
					System.out.println(st1.nval);
					break;
				}
				case StreamTokenizer.TT_WORD: {
					System.out.println(st1.sval);
					break;
				}
				case StreamTokenizer.TT_EOF: {
					break;
				}
				default: {
					System.out.println((char)st1.ttype);
				}
			}
		}
		
	}
}
