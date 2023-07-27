package tw.idv.tibame.tha102.web.userinfo.dao;

import java.util.List;

import tw.idv.tibame.tha102.web.userinfo.vo.UserInfo;



public interface UserInfoDao {
	public void insert(UserInfo users);
    public void update(UserInfo users);
    public List<UserInfo> findByUserName(String user_name);
    public List<UserInfo> findByUserId(Integer user_id);
    public List<UserInfo> getAll();
}
