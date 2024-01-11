package util;

import java.security.MessageDigest;

public class MaHoa {
	// md5
	// sha1 => thường dùng
	
	public static String toSHA1(String str) {
		String salt = "asdfhdhfdfjdkljdksfdhskfh";
		String result = null;
		
		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			// dung cai nay de ma hoa databytes
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) { 
		System.out.println(toSHA1("123456"));
	}
	
}
