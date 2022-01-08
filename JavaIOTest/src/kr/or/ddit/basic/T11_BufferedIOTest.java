package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11_BufferedIOTest {
	
	public static void main(String[] args) throws IOException {
		
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
		
		// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가
		// 8192(8kb)로 설정된다.
		
		// 입출력의 성능 향상을 위해서 버퍼를 이용하는 보조 스트림
		// 버퍼의 크기가 5인 스트림 생성
		BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
		
		for(int i='1'; i<='9'; i++) { // 숫자 자체를 문자로 저장하기
			bos.write(i);
		}
		
		bos.flush(); // 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다. 
		             // close시 자동으로 호출됨.
		
		bos.close();
		
		System.out.println("작업 끝...");
	}

}
