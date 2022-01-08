package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		// 멀티 스레드 프로그램 방식
		
		// 방법1: Thread클래스를 상속한 class의 인스턴스를 생성한 후
		//       이 인스턴스의 start()메서드를 호출한다.
		Thread th1 = new MyThread1();
		th1.run();
		
		// 방법2: Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후
		//       이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로
		//       넘겨준다. 이때 생성된 Thread객체의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1);
		th2.run();
		
		// 방법3: 익명클래스를 이용하는 방법
		//        Runnable인터페이스를 구현한 익명클래스를 Thread인스턴스를
		//        생성할 때 매개변수로 넘겨준다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<=200; i++) {
					System.out.print("@");
					try {
						// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
						// 시간은 밀리세컨드 단위를 사용한다.
						// 즉, 1000(ms)는 1초를 의미한다.
						Thread.sleep(100);
					}catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
				
			}
		});
		
		th3.run();
		
		
	}
}

class MyThread1 extends Thread {
	
	@Override
	public void run() {
		for(int i=0; i<=200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000(ms)는 1초를 의미한다.
				Thread.sleep(100);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i=0; i<=200; i++) {
			System.out.print("$");
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000(ms)는 1초를 의미한다.
				Thread.sleep(100);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
}

