package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}
	
	
	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.insertMember(mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 메일 발송
		
		
		return cnt;
	}

	@Override
	public boolean chkMember(String memId) {
		
		boolean chk = false;
		
		try {
			chk = memDao.chkMember(memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			cnt = memDao.updateMember(mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			cnt = memDao.deleteMember(memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			memList = memDao.getAllMemberList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}


	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = memDao.getSearchMember(mv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memList;
	}

}
