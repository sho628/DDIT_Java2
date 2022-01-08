package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
/*
          한글 인코딩 방식에 대하여...
          
     한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나누어 볼 수 있다.
     원래 한글 윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트에서 EUC-KR방식에서
     확장하였기 때문에 MS949라고도 부른다.
     한글 Wnidows의 메모장에서  이야기하는 ANSI인코딩이란, CP949(Code Page 949)를 말한다.
   CP949는 EUC-KR의 확장이며, 하위 호환성이 있다.
   - MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열)
   - UTF-8 => 유니코드 UTF-8인코딩 방식(영문자 및 숫자: 1byte, 한글: 3byte) => 가변적
   - US-ASCII => 영문 전용 인코딩 방식
   
    ANSI는 영어를 표기하기 위해 만든 코드로 규격자체에 한글이 없었다가 나중에 추가되면서 
    EUC-KR, CP949라는 식으로 확장되었음. 
*/
	public static void main(String[] args) throws IOException {
		// 파일 인코딩 정보를 이용하여 파일 내용 읽기
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		//fis = new FileInputStream("d:/D_Other/test_ansi.txt");
		fis = new FileInputStream("d:/D_Other/test_utf8.txt");
		
		isr = new InputStreamReader(fis, "utf-8");
		
		int c;
		while((c=isr.read()) != -1) {
			System.out.print((char)c);
		}
		
		System.out.println();
		System.out.println("출력 끝...");
		
		isr.close(); // 보조스트림만 닫으면 된다.
	}
}
