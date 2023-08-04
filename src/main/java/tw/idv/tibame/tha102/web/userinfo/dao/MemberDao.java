package tw.idv.tibame.tha102.web.userinfo.dao;

import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

public interface MemberDao {
	public int insert(UserInfo userInfo);
	public int updateUserinfo(UserInfo userInfo);
	public int updatePassword(UserInfo userInfo, int userid);
	public int updateUserpic(UserInfo userInfo, int userid);
	public UserInfo selectByLogin(String userAccount, String password);
	public UserInfo selectByUserAccount(String userAccount);
	public UserInfo selectByUserId(Integer userid);
	public int updateSellerIdentityByUserId(int sellerIdentity, int userid);
}
