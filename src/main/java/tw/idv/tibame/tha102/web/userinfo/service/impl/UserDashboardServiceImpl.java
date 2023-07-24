package tw.idv.tibame.tha102.web.userinfo.service.impl;

import tw.idv.tibame.tha102.web.userinfo.dao.MemberDao;
import tw.idv.tibame.tha102.web.userinfo.dao.MemberDaoImpl;
import tw.idv.tibame.tha102.web.userinfo.service.UserDashboardService;
import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

public class UserDashboardServiceImpl implements UserDashboardService{
	MemberDao memberDaoImpl;
	public UserDashboardServiceImpl() {
		memberDaoImpl = new MemberDaoImpl();
	}
	@Override
	public UserInfo pillsProfile(Integer userid) {
		UserInfo userInfo = memberDaoImpl.selectByUserId(userid);
		if(userInfo == null) {
			return userInfo;
		}
		return null;
	}

}
