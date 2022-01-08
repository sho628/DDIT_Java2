package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		System.out.println("static변수값 : " + PrintAnnotation.id);
		
		// reflection 기능을 이용한 메서드 실행하기
		// 선언된 메서드 목록 가져오기
		Method[] declaredMethods = Service.class.getDeclaredMethods();
		
		for(Method m : declaredMethods) {
			System.out.println(m.getName()); // 메서드명 출력
			
			// Annotation 객체 가져오기
			PrintAnnotation printAnn = 
					m.getDeclaredAnnotation(PrintAnnotation.class);
			
			for(int i=0; i<printAnn.count(); i++) { // count값 만큼...
				System.out.print(printAnn.value()); // value값 출력...
			}
			
			System.out.println(); // 줄바꿈 처리
			
			// 메서드 실행하기
			Class<?> clazz = Service.class;
			try {
				Service service = (Service) clazz.newInstance();
				m.invoke(service); // 메서드 실행
			}catch(InstantiationException ex) {
				ex.printStackTrace();
			}
		}
	}
}
