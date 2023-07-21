package tw.idv.tibame.tha102.web.orderproduct.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

@Repository
public class OrderProductDaoImpl implements OrderProductDao {
	@PersistenceContext
	private Session session;

    public void insert(OrderProduct orderProduct) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(orderProduct);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(OrderProduct orderProduct) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(orderProduct);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<OrderProduct> getAll() {
        List<OrderProduct> orderProducts = null;

        try {
            Query<OrderProduct> query = session.createQuery("FROM OrderProduct", OrderProduct.class);
            orderProducts = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orderProducts;
    }

    public List<OrderProduct> findByUserId(Integer user_id) {
        List<OrderProduct> orderProducts = null;

        try {
            Query<OrderProduct> query = session.createQuery("FROM OrderProduct WHERE user_id = :userId", OrderProduct.class);
            query.setParameter("userId", user_id);
            orderProducts = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return orderProducts;
    }
}
