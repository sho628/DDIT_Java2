package kr.or.ddit.basic;

import java.text.DecimalFormat;

public class PlanetRadius {

	
	enum Planet{
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232),
		천왕성(25362), 해왕성(24622);
		
		private final int radius;
		
		Planet(int radius){
			this.radius = radius;
		}
		
		public int getRadius() {
			return radius;
		}
		
		public double calArea() {
			double pai = 3.1415926535;
			
			//면적 계산식
			double cal1 = 4 * Math.PI * Math.pow(radius, 2);
			//소수점 제거
			double cal2 = Math.round(cal1);
						
			return cal2;
		}
		
	}
	
	public static void main(String[] args) {
		//숫자에 3자리마다 ,찍기
		DecimalFormat df = new DecimalFormat(",###");
		
		for(Planet p : Planet.values()) {
			System.out.println(p.name());
			System.out.println(p.name() + "의 반지름 : " + df.format(p.getRadius()) + "KM");
			System.out.println(p.name() + "의 표면적 : " + df.format(p.calArea()) + "㎢");
			System.out.println("------------------------------");
		}
	}
	
}
