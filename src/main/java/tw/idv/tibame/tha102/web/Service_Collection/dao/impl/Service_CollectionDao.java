package tw.idv.tibame.tha102.web.Service_Collection.dao.impl;

import tw.idv.tibame.tha102.web.Service_Collection.vo.Service_Collection;

public interface Service_CollectionDao {
	
	void Insert_Ser_Coll(Service_Collection ser_coll);
	
	boolean Select_SerById(Service_Collection ser_coll);
	
	void Delete_SerById(Service_Collection ser_coll);
	
	
}
