package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Tulips {

	public static void main(String[] args) {
		
		FileInputStream fis;
		FileOutputStream fos;
		
		try {
			File file = new File("d:/D_Other/Tulips.jpg");
			fis = new FileInputStream(file);
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			int c;
			
			while((c=fis.read()) != -1) {
				fos.write(c);
			}
			System.out.println("저장됨");
			
			fis.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("지정할 파일이 없습니다.");
			
		} catch (IOException e) {
			System.out.println("알 수 없는 입출력 오류입니다.");
		}
		
	}
}
