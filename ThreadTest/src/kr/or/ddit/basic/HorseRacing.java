package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRacing {
	
	public static void main(String[] args) {
		List<Horse> horseList = new ArrayList<Horse>();
		Thread racingStart = new RacingStart(horseList);
		for(int i = 1; i <= 10; i++) {
			horseList.add(new Horse("말" + i));
		}
		
		racingStart.start();
		try {
			racingStart.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Collections.sort(horseList);
		
		System.out.println("==============================");
		for(int i = 0; i < horseList.size(); i++) {
			System.out.println(horseList.get(i).getRank() + "등말 : " + horseList.get(i).getHorseName());
		}
		System.out.println("==============================");
		
	}
}

class RacingStart extends Thread {

	public static int rank = 1;
	private List<Horse> horseList = new ArrayList<Horse>();
	
	public RacingStart(List<Horse> horseList) {
		this.horseList = horseList;
	}

	@Override
	public void run() {
		for(int i = 0; i < horseList.size(); i++) {
			horseList.get(i).start();
		}
		
		while(true) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < horseList.size(); i++) {
				System.out.print((i+1) + "번말 ");
				for(int j = 1; j <= 50; j++) {
					if(j == horseList.get(i).getPosition()) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			if(rank > 10) return;
		}
	}
	
}

class Horse extends Thread implements  Comparable<Horse> {
	String horseName;
	int rank;
	int position;
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	@Override
	public void run() {
		for(int i = 1; i <= 50; i++) {
			try {
				Thread.sleep((int)(Math.random() * 200 + 100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			position++;
		}
		rank = RacingStart.rank++;
	}

	@Override
	public int compareTo(Horse hs) {
		return new Integer(rank).compareTo(hs.getRank());
	}
	
	
}