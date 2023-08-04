package tw.idv.tibame.tha102.web.userinfo.service;

import java.lang.reflect.Member;

import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

public interface MemberService {
	//註冊
	UserInfo register(UserInfo userInfo);
	//登入
	UserInfo login(UserInfo userInfo);
	//Header
	UserInfo header(int user_id);
	//修改會員資訊
	UserInfo changeUserInfo(UserInfo userInfo);
	//修改密碼
	UserInfo changeUserPassword(UserInfo userInfo, int userid);
	//修改大頭貼
	UserInfo changeUserPic(UserInfo userInfo, int userid);
}
