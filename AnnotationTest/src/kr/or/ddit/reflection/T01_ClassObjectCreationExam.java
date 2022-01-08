package kr.or.ddit.reflection;

/*
    Java Reflection 에 대하여...
    
  1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한정보를 가져오거나 수정할 수 있고,
         새로운 객체를 생성하거나, 메서드를 실행할 수 있다.
     (컴파일 시점에 해당 정보를 알수 없는 경우에 유용하게 사용될 수 있다.)
  2. Reflection API는 java.lang.reflect패키지와 lava.lang.Class를 통해 제공된다.
  3. java.lang.Class의 주요 메서드
     - getName(), getSuperclass(), getInterfaces(), getModifier() 등.
  4. java.lang.reflect 패키지의 주요 클래스
     - Field, Method, Constructor, Modifier 등.
  5. Reflection API를 이용하면 클래스의 private 메서드나 변수에 접근 가능하다.(보안 위협)
  6. new 를 통한 객체 생성에 비해 수행속도가  약간 느리다(오버헤드 발생)   
     
*/
public class T01_ClassObjectCreationExam {
	public static void main(String[] args) throws ClassNotFoundException {
		
		// 첫번째 방법 : Class.forName() 메서드 이용
		Class<?> klass = Class.forName(
				"kr.or.ddit.reflection.T01_ClassObjectCreationExam");
		
		// 두번째 방법 : getClass() 메서드 이용
		T01_ClassObjectCreationExam obj = new T01_ClassObjectCreationExam();
		klass = obj.getClass();
		
		// 세번째 방법 : .class 이용
		klass = T01_ClassObjectCreationExam.class;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
