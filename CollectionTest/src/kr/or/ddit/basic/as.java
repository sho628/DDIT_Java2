package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class as {

public void start() {
		
		while(true) {
		System.out.println("====================================");
		System.out.println("\t Lotto 프로그램");
		System.out.println("------------------------------------");
		System.out.println("1.Lotto 구입");
		System.out.println("0.프로그램 종료");
		System.out.print("▶");
		int input = ScanUtil.nextInt();
	
			switch(input) {
			case 1: buy(); break;
			case 0: System.out.println("잠시 후 프로그램이 종료됩니다");
					System.exit(0);
			}
		}
	}
	
	public void buy() {
		System.out.println("====================================");
	System.out.println("\t Lotto 구입 시작 \t");
	System.out.println("------------------------------------");
	System.out.println("가격은 번호 1개 당 1000원입니다");
	System.out.println("금액을 제시해주세요.");
	System.out.print("▶");
	int money = ScanUtil.nextInt();				//결제 금액
	int ticket = (int)Math.round(money / 1000); //횟수
	int coin = money % 1000;					//거스름 돈
	
	pick(money, ticket, coin); 
		
	}
	
	
	public void pick(int money, int ticket, int coin) {
		
		Set<Integer> lotto = new HashSet<Integer>();
		
		int count = ticket + 1;
		int number = 1;
		
		System.out.println("====================================");
		System.out.println("\t 행운의 로또번호 출력");
		System.out.println("------------------------------------");
		while(count > 1) {
			while(lotto.size() < 6) {
				lotto.add((int)(Math.random() * 45 + 1));
			}
			System.out.println("로또번호 " + number + " : " + lotto);
			lotto.clear();
			number++;
			count--;
		}System.out.println("====================================");
		
		System.out.println("결제 금액 : " +money);
		System.out.println("구입 횟수 : " +ticket);
		System.out.println("거스름돈 : " + coin);

	}


	public static void main(String[] args) {
		
		as lotto = new as();
		
		lotto.start();
		
	}	
	
}
