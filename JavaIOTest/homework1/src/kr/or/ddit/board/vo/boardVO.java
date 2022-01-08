package kr.or.ddit.board.vo;

/**
 * DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다.
 * 
 * @author pc17
 *<p>
 * DB테이블의 '컬럼'이 이 클래스의 '멤버변수' 가 된다<br>
 * Db테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.<br>
 * </p>
 */
public class boardVO {
	private int num;
	private String borTit;
	private String borWriter;
	private String borContent;
	private String borDate;
	@Override
	public String toString() {
		return "boardVO [num=" + num + ", borTit=" + borTit + ", borWriter=" + borWriter + ", borContent=" + borContent
				+ ", borDate=" + borDate + "]";
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBorTit() {
		return borTit;
	}
	public void setBorTit(String borTit) {
		this.borTit = borTit;
	}
	public String getBorWriter() {
		return borWriter;
	}
	public void setBorWriter(String borWriter) {
		this.borWriter = borWriter;
	}
	public String getBorContent() {
		return borContent;
	}
	public void setBorContent(String borContent) {
		this.borContent = borContent;
	}
	public String getBorDate() {
		return borDate;
	}
	public void setBorDate(String borDate) {
		this.borDate = borDate;
	}
	
	
	
	
}
