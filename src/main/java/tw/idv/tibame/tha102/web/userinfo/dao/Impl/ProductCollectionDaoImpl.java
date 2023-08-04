package tw.idv.tibame.tha102.web.userinfo.dao.Impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.idv.tibame.tha102.web.product.vo.Product;
import tw.idv.tibame.tha102.web.userinfo.dao.ProductCollectionDao;
import tw.idv.tibame.tha102.web.userinfo.vo.ProductCollection;
@Repository
public class ProductCollectionDaoImpl implements ProductCollectionDao {
	@PersistenceContext
	private Session session;
	
	@Override
	public int insert(ProductCollection productCollection) {
		try {
			productCollection.setProductCollectTime(new Timestamp(System.currentTimeMillis()));
			session.persist(productCollection);
			return productCollection.getUserId();
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int delete(String productName, int userId) {
		String nativeSql = """
				DELETE pc FROM product_collection pc 
					JOIN product p 
						ON pc.product_id = p.product_id 
							WHERE pc.user_id = :userId AND p.product_name = :productName
				""";
		Query query = session.createNativeQuery(nativeSql);
		query.setParameter("productName", productName);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}

	@Override
	public List<Product> selectAllbyUserId(int userId) {
		try {
			String hqlString ="""
					SELECT 
						FROM ProductCollection pc
							JOIN product p
								ON pc.product_id = p.product_id 
									WHERE userId = :userId
					""";
			Query<Product> query = session.createQuery(hqlString, Product.class);
			query.setParameter("userId", userId);
			return query.getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}	
	}

	@Override
	public byte[] selectPicByproductCollectId(int productCollectId) {
		// TODO Auto-generated method stub
		return null;
	}

}
