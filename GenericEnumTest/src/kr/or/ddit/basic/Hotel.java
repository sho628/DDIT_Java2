package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;

class Room {
	private int num;
	private String name;

	public Room(int num, String name) {
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

public class Hotel {
	static HashMap<Integer, Room> hotelRoom = new HashMap<>();

	public static void main(String[] args) {
		System.out.println("*****************************");
		System.out.println("호텔문을 열었습니다");
		System.out.println("*****************************");
		while (true) {
			System.out.println();
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.print("메뉴선택=>");
			Scanner sc = new Scanner(System.in);
			int input = 0;
			input = Integer.parseInt(sc.nextLine());

			switch (input) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomInfo();
				break;
			case 4:
				System.out.println("*****************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("*****************************");
				System.exit(0);
				break;
			}
		}
	}

	private static void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력=>");
		Scanner sc = new Scanner(System.in);
		int num = 0;
		num = Integer.parseInt(sc.nextLine());
		// containsKey -> Map 안에 key가 있는지 없는지 판단
		if (hotelRoom.containsKey(num) == true) {
			System.out.println(num + "호 객실은 이미 손님이 있습니다.");
		} else {
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력=>");
			String name = null;
			name = sc.nextLine();
			Room room = new Room(num, name); // Room 클래스를 사용하기위해 변수 room 선언 Room클래스에 방번호와 투숙객이름을 저장
			hotelRoom.put(num, room); // 방번호를 키로 잡아서 해쉬맵에 저장
			System.out.println("체크인 되었습니다");
		}
	}

	private static void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력=>");
		Scanner sc = new Scanner(System.in);
		int num = 0;
		num = Integer.parseInt(sc.nextLine());
		hotelRoom.remove(num); // 입력받은 방번호를 삭제
		System.out.println("체크아웃 되었습니다.");
	}

	private static void roomInfo() {
		System.out.println("--------현재 객실 상태--------");
		for (int key : hotelRoom.keySet()) { // 해쉬맵안에 있는 키(방번호)를 반복문으로 전부 가져온다
			Room room = hotelRoom.get(key);
			// 현재 객실 룸번호와 투숙객 정보를 출력한다.
			System.out.println("룸번호 : " + room.getNum() + " 투숙객 : " + room.getName());
		}
	}

}