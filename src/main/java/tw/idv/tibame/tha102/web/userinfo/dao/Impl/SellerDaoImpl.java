package tw.idv.tibame.tha102.web.userinfo.dao.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.idv.tibame.tha102.web.userinfo.dao.SellerDao;
import tw.idv.tibame.tha102.web.userinfo.vo.Seller;
@Repository
public class SellerDaoImpl implements SellerDao{
	//注入hibernate的session
	@PersistenceContext
	private Session session;
	
	
	@Override
	public int insertSeller(Seller seller) {
		try {
			session.persist(seller);
			return seller.getSellerId();
		} catch (Exception e) {
			return 0;
		}
		
	}

	@Override
	public boolean updateSeller(Seller seller, int userId) {
		try {
			//開啟交易
			Transaction tx = session.beginTransaction();
			Seller sellerOld = selectSellerById(userId);
			if(sellerOld == null) {
				tx.rollback();
				return false;
			}
			sellerOld.setBankCode(seller.getBankCode());
			sellerOld.setBankHolderName(seller.getBankHolderName());
			sellerOld.setBankAccount(seller.getBankAccount());
			tx.commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			return false;
		}
		
	}

	@Override
	public Seller selectSellerById(int userId) {
		try {
			String hqlString = """
					FROM Seller WHERE userId = :userId
					""";
			Query<Seller> query = session.createQuery(hqlString, Seller.class);
			query.setParameter("userId", userId);
			return query.getSingleResult();
		} catch (Exception e) {
			Seller seller = new Seller();
			return seller;
		}
	}

}
