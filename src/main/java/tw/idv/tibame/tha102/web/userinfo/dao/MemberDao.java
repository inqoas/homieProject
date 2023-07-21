package tw.idv.tibame.tha102.web.userinfo.dao;

import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

public interface MemberDao {
	public int insert(UserInfo userInfo);
	public int update(UserInfo userInfo);
	public UserInfo selectByLogin(String userAccount, String password);
	public UserInfo selectByUserAccount(String userAccount);
	public UserInfo selectByUserId(Integer userid);
}
