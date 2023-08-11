package tw.idv.tibame.tha102.web.userinfo.dao.Impl;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.idv.tibame.tha102.web.userinfo.vo.Product;
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
			String nativeSql ="""
					SELECT p.product_id AS product_id, p.product_name AS product_name, p.product_price AS product_price, p.product_category AS product_category
						FROM product_collection pc
							JOIN product p
								ON pc.product_id = p.product_id 
									WHERE pc.user_id = :user_id
					""";
			Query query = session.createNativeQuery(nativeSql);
			query.setParameter("user_id", userId);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}	
	}

	@Override
	public byte[] selectPicByproductCollectId(int productId) {
		try {
			String hqlString ="""
					SELECT productPicture FROM Product WHERE productId = :productId
					""";
		Query<byte[]> query = session.createQuery(hqlString, byte[].class);
		query.setParameter("productId", productId);
		return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public ProductCollection selectProductCollectionByUserIdAndProductId(int userId, int productId) {
		try {
			String hqlString = """
						FROM ProductCollection
							WHERE userId = :userId and productId = :productId
						""";
			Query<ProductCollection> query = session.createQuery(hqlString, ProductCollection.class);
			query.setParameter("userId", userId);
			query.setParameter("productId", productId);
			ProductCollection productCollection = query.getSingleResult();
			return productCollection;
		} catch (Exception e) {
			return null;
		}
	}

}
