package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T05_WildCardTest {
	public static void main(String[] args) {
		
		FruitBox<Fruit> fruitBox = new FruitBox<Fruit>(); // 과일상자
		FruitBox<Apple> appleBox = new FruitBox<Apple>(); // 사과상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		Juicer.makeJuice(fruitBox); // 과일상자인 경우에는 아무런 문제 없음.
		Juicer.makeJuice(appleBox); // 제너릭 메서드 이용하여 해결.
		
		FruitBox<?> tempBox;
	}
}

class Juicer {
/*
     와일드 카드에 대하여...
     
     와일드카드(?)는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의 인수
   (Argument)로서, 변수선언, 객체생성 및 메서드를 정의할 때 사용된다.
   => 제너릭 타입 선언시에는 사용할 수 없다.
   
   <? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능
   <? super T>   => 와일드 카드의 하한 제한. T와 그 조상들만 가능
   <?>           => 허용 가능한 모든 타입이 가능 <? extends Object> 
*/
	
	//static void makeJuice(FruitBox<Fruit> box) {
	//static <T extends Fruit> void makeJuice(FruitBox<T> box) {
	static void makeJuice(FruitBox<?> box) {
		
		String fruitListStr = ""; // 과일목록
		
		int cnt = 0; 
		for(Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		
		System.out.println(fruitListStr + " => 쥬스 완성!!!");
	}
}

/**
 * 과일
 */
class Fruit{
	private String name; // 과일 이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}
}

class Apple extends Fruit {

	public Apple() {
		super("사과");
	}
}

class Grape extends Fruit {

	public Grape() {
		super("포도");
	}
}

class FruitBox<T extends Fruit> {
	
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}








