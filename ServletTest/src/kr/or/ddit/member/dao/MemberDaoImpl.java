package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.comm.vo.PagingVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {
	
	private static IMemberDao memDao;
	
	private SqlMapClient smc;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getInstance() {
		
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		
		return memDao;
	}
	
	@Override
	public int insertMember(MemberVO mv) throws SQLException {
		
		int cnt = smc.update("member.insertMember", mv);
		
		return cnt;
	}

	@Override
	public boolean chkMember(String memId) throws SQLException {
		
		boolean chk = false; // 중복여부 확인용 플래그 
		
		int cnt = (int) smc.queryForObject("member.checkMember", memId);
		
		if(cnt > 0) {
			chk = true;
		}
		
		return chk;
	}

	@Override
	public int updateMember(MemberVO mv) throws SQLException {
		int cnt = 0;
		
		cnt = smc.update("member.updateMember", mv);
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.delete("member.deleteMember", memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList(PagingVO pagingVO) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		memList = smc.queryForList("member.getMemberAll", pagingVO);
		
		return memList;
	}
	
	@Override
	public int getAllMemberCount() throws SQLException {
		int cnt = (int) smc.queryForObject("member.getAllMemberCount");
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<>();
		
		memList = smc.queryForList("member.getSearchMember", mv);
		
		return memList;
	}

	@Override
	public MemberVO getMember(String memId) throws SQLException {
		
		MemberVO mv = (MemberVO)smc
					.queryForObject("member.getMember", memId);
		
		return mv;
	}

}
