package tw.idv.tibame.tha102.web.userinfo.dao;

import tw.idv.tibame.tha102.web.userinfo.vo.Seller;

public interface SellerDao {
	public int insertSeller(Seller seller);
	public boolean updateSeller(Seller seller, int userId);
	public Seller selectSellerById(int userId);
	public int updateUserStatusByUserId(int userStatus, int userid);
}
