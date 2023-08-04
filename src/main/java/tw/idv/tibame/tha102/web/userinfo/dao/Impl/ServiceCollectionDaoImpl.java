package tw.idv.tibame.tha102.web.userinfo.dao.Impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.idv.tibame.tha102.web.service.vo.Service;
import tw.idv.tibame.tha102.web.userinfo.dao.ServiceCollectionDao;
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
								WHERE sc.user_id = :userId AND s.service_name = :serviceName;
					""";
			Query<Void> query = session.createNativeQuery(nativeSql, Void.class);
			query.setParameter("userId", userId);
			query.setParameter("serviceName", serciveName);
			return query.executeUpdate();
			
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Service> selectAllByUserId(int userId) {
		try {
			String hqlString = """
					FROM ServiceCollection sc
						JOIN Service s
							ON sc.serviceId = s.serviceId
								WHERE sc.userId = :userId;
					""";
			Query<Service> query = session.createQuery(hqlString, Service.class);
			query.setParameter("userId", userId);
			return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
		
	}

	@Override
	public byte[] selectPicByServiceId(int serviceCollectionId) {
		try {
			String hqlString = """
					SELECT servicePicture FROM ServiceCollection sc
						JOIN Service s
							ON sc.serviceId = s.serviceId
								WHERE sc.serviceCollectionId = :serviceCollectionId;
					""";
		Query<byte[]> query = session.createQuery(hqlString, byte[].class);
		query.setParameter("serviceCollectionId", serviceCollectionId);
		byte[] pic = query.getSingleResult();
		return pic;
		} catch (Exception e) {
			return null;
		}
	}

}
