package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 자료를 읽을때 사용할 바이트 배열 선언
		
		ByteArrayInputStream bais = 
				new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos =
				new ByteArrayOutputStream();
		
		// available() => 읽어 올 수 있는 byte수를 반환
		while(bais.available() > 0) {
			/*
			bais.read(temp);// temp배열 크기만큼 자료를 읽어와 temp에 저장함.
			baos.write(temp);// temp배열의 내용을 출력한다.
			*/
			
			// 실제 읽어온 byte수를 반환한다.
			int len = bais.read(temp);
			
			// temp배열의 내용 중에서 0번째 붙 len개수 만큼 출력한다.
			baos.write(temp, 0, len);
			
			System.out.println("temp : " + Arrays.toString(temp));
		}
		
		// 출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		// 스트림 객체 닫아주기
		bais.close();
		baos.close();
		
		
		
		
	}
			
		
}
