package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Homework {
	
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("001", "홍길동",80,80,50));
		stuList.add(new Student("005", "김길동",80,60,30));
		stuList.add(new Student("003", "윤길동",50,50,40));
		stuList.add(new Student("004", "이길동",60,90,90));
		stuList.add(new Student("008", "박길동",100,10,80));
		stuList.add(new Student("010", "고길동",100,10,80));
		Collections.sort(stuList);
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("========================================================================");
		
		Collections.sort(stuList, new SortNumDesc1());
		int a=1;
		for(Student stu : stuList) {
			stu.setRank(a++);
			System.out.println(stu);
		}
	
	
	}

}
class Student implements Comparable<Student>{
	private String num;
	private String name;
	private int kscore;
	private int escore;
	private int mscore;
	private int ascore;
	private int rank;
	@Override
	public int compareTo(Student stu) {
		
		return getNum().compareTo(stu.getNum());
	}
	
	


	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kscore=" + kscore + ", escore=" + escore + ", mscore="
				+ mscore + ", ascore=" + ascore + ", rank=" + rank + "]";
	}



	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKscore() {
		return kscore;
	}
	public void setKscore(int kscore) {
		this.kscore = kscore;
	}
	public int getEscore() {
		return escore;
	}
	public void setEscore(int escore) {
		this.escore = escore;
	}
	public int getMscore() {
		return mscore;
	}
	public void setMscore(int mscore) {
		this.mscore = mscore;
	}
	public int getAscore() {
		return ascore;
	}
	public void setAscore(int ascore) {
		this.ascore = ascore;
	}
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}



	public Student(String num, String name, int kscore, int escore, int mscore) {
		super();
		this.num = num;
		this.name = name;
		this.kscore = kscore;
		this.escore = escore;
		this.mscore = mscore;
		this.ascore = kscore+escore+mscore;
	}
	
	
}
class SortNumDesc1 implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getAscore()> stu2.getAscore()) {
			return -1;
		}else if(stu1.getAscore() == stu2.getAscore()) {
			
			return stu1.getNum().compareTo(stu2.getNum())*-1;
		}else {
			return 1;
		}
		
		
		//Wrapper클래스에서 제공하는메서드를 이용하는 방법
		//return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
	}
	
}