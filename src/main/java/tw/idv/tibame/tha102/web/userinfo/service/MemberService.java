package tw.idv.tibame.tha102.web.userinfo.service;

import java.lang.reflect.Member;

import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;

public interface MemberService {
	//註冊
	UserInfo register(UserInfo userInfo);
	//登入
	UserInfo login(UserInfo userInfo);
}
