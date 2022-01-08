package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 입력용 스트림 객체 생성하기
		ByteArrayInputStream bais = null; //  스트림 객체 변수선언
		bais = new ByteArrayInputStream(inSrc); // 스트림 객체 생성
		
		// 출력용 스트림 객체 생성
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int data;	// 읽어온 자료를 저장할 변수 선언
		
		// read()메서드 => byte단위로 자료를 읽어와 int형으로 반환한다.
		//            => 더이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = bais.read()) != -1) {
			baos.write(data); // 스트림으로 출력하기
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
