package kr.or.ddit.basic;

public class Service {
	
	@PrintAnnotation
	public void method1() {
		System.out.println("method1()에서 출력되었습니다.");
	}
	
	@PrintAnnotation("%")
	public void method2() {
		System.out.println("method2()에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value="#", count=30)
	public void method3() {
		System.out.println("method3()에서 출력되었습니다.");
	}
}
