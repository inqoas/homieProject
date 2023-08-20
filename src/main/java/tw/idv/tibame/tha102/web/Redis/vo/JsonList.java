package tw.idv.tibame.tha102.web.Redis.vo;

import java.util.List;

public class JsonList {

	private List<Redis_Data> Redis_Data ;
	
	private List<Redis_Data_Service> Redis_Data_Service;

	public List<Redis_Data_Service> getRedis_Data_Service() {
		return Redis_Data_Service;
	}

	public void setRedis_Data_Service(List<Redis_Data_Service> redis_Data_Service) {
		Redis_Data_Service = redis_Data_Service;
	}

	public List<Redis_Data> getRedis_Data() {
		return Redis_Data;
	}

	public void setRedis_Data(List<Redis_Data> redis_Data) {
		Redis_Data = redis_Data;
	}
	
}