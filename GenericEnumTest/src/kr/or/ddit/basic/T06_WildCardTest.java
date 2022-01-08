package kr.or.ddit.basic;

import java.util.Arrays;

public class T06_WildCardTest {
	
	/**
	 * 모든 과정 수강정보 조회
	 * @param course
	 */
	public static void listCourseInfo(Course<?> course) {
		System.out.println(course.getName() + " 수강생: " 
				+ Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 학생과정 수강정보 조회
	 * @param course
	 */
	public static void listStudentCourseInfo(
					Course<? extends Student> course) {
		System.out.println(course.getName() + " 수강생: " 
				+ Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 근로자 과정 수강정보 조회
	 * @param course
	 */
	public static void listWorkerCourseInfo(
					Course<? super Worker> course) {
		System.out.println(course.getName() + " 수강생: " 
				+ Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		
		Course<Person> personCourse = new Course<>("일반인과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workerCourse = new Course<>("직장인과정", 5);
		workerCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course<>("학생과정", 5); 
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
		
		Course<HighStudent> highStudentCourse = 
				new Course<>("고등학생과정", 5);
		highStudentCourse.add(new HighStudent("고등학생1"));
		
		listCourseInfo(personCourse);
		listCourseInfo(workerCourse);
		listCourseInfo(studentCourse);
		listCourseInfo(highStudentCourse);
		System.out.println("----------------------------");
		
		//listStudentCourseInfo(personCourse);
		//listStudentCourseInfo(workerCourse);
		listStudentCourseInfo(studentCourse);
		listStudentCourseInfo(highStudentCourse);
		System.out.println("----------------------------");
		
		listWorkerCourseInfo(personCourse);
		listWorkerCourseInfo(workerCourse);
		//listWorkerCourseInfo(studentCourse);
		//listWorkerCourseInfo(highStudentCourse);
		
		
		
		
		
		
	}
	
	

}

// 일반인
class Person {
	String name; // 이름

	public Person(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "이름:" + name;
	}
}

// 근로자
class Worker extends Person {
	public Worker(String name) {
		super(name);
	}
}

// 학생
class Student extends Person {
	public Student(String name) {
		super(name);
	}
}

// 고등학생
class HighStudent extends Student {
	public HighStudent(String name) {
		super(name);
	}
}

// 수강과정
class Course<T> {
	private String name; // 과정명
	private T[] students; // 수강생(제너릭배열)
	
	public Course(String name, int capacity) {
		this.name = name;
		
		// 타입 파라미터로배열을 생성시 오브젝트 배열을 생성후, 타입 파라미터 배열로 캐스팅
		// 처리해야 함.
		students = (T[])(new Object[capacity]);
	}

	public String getName() {
		return name;
	}

	public T[] getStudents() {
		return students;
	}
	
	// 수강생 등록
	public void add(T t) {
		for(int i=0; i<students.length; i++) {
			if(students[i] == null) {// 아직 등록되지 않은 빈 자리인지 확인
				students[i] = t;
				break;
			}
		}
	}
}


