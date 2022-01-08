package kr.or.ddit.basic;

import java.util.Scanner;

public class ScanUtil {
		
		private static Scanner s = new Scanner(System.in);
		
		//메서드에는 왜 static을 붙일까?
		//자주 사용되는 메서드에 매번 객체 생성을 하지 않으려고
		
		public static String nextLine(){		//이제 매번 객체 생성을 하지 않아도 된다
			return s.nextLine();
		}
		
		public static int nextInt(){			//nextInt 오류나서 잘 안썼는데 이제 써도 된다
			return Integer.parseInt(s.nextLine());
		
}
}
		
