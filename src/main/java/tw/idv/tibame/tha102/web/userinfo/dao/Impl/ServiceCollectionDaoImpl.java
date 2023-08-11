package tw.idv.tibame.tha102.web.userinfo.dao.Impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;

import tw.idv.tibame.tha102.web.userinfo.dao.ServiceCollectionDao;
import tw.idv.tibame.tha102.web.userinfo.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.vo.ServiceCollection;
@Repository
public class ServiceCollectionDaoImpl implements ServiceCollectionDao{
	@PersistenceContext
	private Session session;
	
	@Override
	public int insert(ServiceCollection serviceCollection) {
		try {
			session.persist(serviceCollection);
			return serviceCollection.getServiceId();
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public int delete(String serciveName, int userId) {
		try {
			String nativeSql ="""
					DELETE sc FROM service_collection sc
						JOIN service s
							ON sc.service_id = s.service_id
								WHERE sc.user_id = :userId AND s.service_name = :serviceName
					""";
			Query query = session.createNativeQuery(nativeSql);
			query.setParameter("serviceName", serciveName);
			query.setParameter("userId", userId);
			return query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Service> selectAllByUserId(int userId) {
		try {
			String nativeSql = """
				SELECT s.service_id, s.service_name, s.service_price, s.service_id_code
					FROM service_collection sc
						JOIN service s
							ON sc.service_id = s.service_id
								WHERE sc.user_id = :user_id
					""";
			Query query = session.createNativeQuery(nativeSql);
			query.setParameter("user_id", userId);
			return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
		
	}

	@Override
	public byte[] selectPicByServiceId(int serviceId) {
		try {
			String hqlString = """
					SELECT servicePicture FROM Service WHERE serviceId = :serviceId
					""";
		Query<byte[]> query = session.createQuery(hqlString, byte[].class);
		query.setParameter("serviceId", serviceId);
		byte[] pic = query.getSingleResult();
		return pic;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ServiceCollection selectServiceCollectionByUserIdAndServiceId(int userId, int serviceId) {
	    try {
	        String hqlString = """
	                FROM ServiceCollection
	                WHERE userId = :userId and serviceId = :serviceId
	                """;
	        Query<ServiceCollection> query = session.createQuery(hqlString, ServiceCollection.class);
	        query.setParameter("userId", userId);
	        query.setParameter("serviceId", serviceId);
	        ServiceCollection serviceCollection = query.getSingleResult();
	        return serviceCollection;
	    } catch (Exception e) {
	        return null;
	    }
	}


}
