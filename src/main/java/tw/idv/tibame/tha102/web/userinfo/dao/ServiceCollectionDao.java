package tw.idv.tibame.tha102.web.userinfo.dao;

import java.util.List;

import tw.idv.tibame.tha102.web.userinfo.vo.ProductCollection;
import tw.idv.tibame.tha102.web.userinfo.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.vo.ServiceCollection;

public interface ServiceCollectionDao {
	public int insert(ServiceCollection serviceCollection);
	public int delete(String serciveName, int userId);
	public ServiceCollection selectServiceCollectionByUserIdAndServiceId(int userId, int productId);
	public List<Service> selectAllByUserId(int userId);
	public byte[] selectPicByServiceId(int serviceCollectionId);
}
