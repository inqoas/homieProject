package tw.idv.tibame.tha102.web.userinfo.service.impl;

import tw.idv.tibame.tha102.web.userinfo.dao.MemberDao;
import tw.idv.tibame.tha102.web.userinfo.dao.Impl.MemberDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.service.UserDashboardService;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

public class UserDashboardServiceImpl implements UserDashboardService{
	MemberDao memberDaoImpl;
	public UserDashboardServiceImpl() {
		memberDaoImpl = new MemberDaoImpl();
	}
	@Override
	public UserInfo userProfile(Integer userid) {
		UserInfo userInfo = memberDaoImpl.selectByUserId(userid);
		if(userInfo == null) {
			userInfo.setMessage("會員資料載入失敗，請重新登入");
			userInfo.setSuccess(false);
			return userInfo;
		}
		userInfo.setSuccess(true);
		return userInfo;
	}

}
