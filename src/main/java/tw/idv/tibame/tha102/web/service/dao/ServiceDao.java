package tw.idv.tibame.tha102.web.service.dao;

import java.util.List;

import tw.idv.tibame.tha102.web.service.vo.Service;

public interface ServiceDao {

	//Service_All
	List<Service> Service_All();
	///新增
	Integer add_service(Service service);
	//查圖片
	Service select_ImgbyId(Integer id);
	
}
