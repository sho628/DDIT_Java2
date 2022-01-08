package kr.or.ddit.board.vo;


public class BoardVO {
	private String bno;
	private String btitle;
	private String bwriter;
	private String bdate;
	private String bcontent;
	
	@Override
	public String toString() {
		return "BoardVO [no=" + bno + ", title=" + btitle + ", writer=" + bwriter + ", date=" + bdate + ", content="
				+ bcontent + "]";
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	

	
}