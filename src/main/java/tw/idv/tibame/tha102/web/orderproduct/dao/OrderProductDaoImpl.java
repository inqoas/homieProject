package tw.idv.tibame.tha102.web.orderproduct.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

@Repository
public class OrderProductDaoImpl implements OrderProductDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(OrderProduct orderProduct) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(orderProduct);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to insert OrderProduct.", e);
        }
    }

    public void update(OrderProduct orderProduct) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(orderProduct);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update OrderProduct.", e);
        }
    }

    public List<OrderProduct> getAll() {
        List<OrderProduct> orderProducts = null;

        try (Session session = sessionFactory.openSession()) {
            Query<OrderProduct> query = session.createQuery("FROM OrderProduct", OrderProduct.class);
            orderProducts = query.list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all OrderProduct.", e);
        }

        return orderProducts;
    }

    public List<OrderProduct> findByUserId(Integer user_id) {
        List<OrderProduct> orderProducts = null;

        try (Session session = sessionFactory.openSession()) {
            Query<OrderProduct> query = session.createQuery("FROM OrderProduct WHERE user_id = :userId", OrderProduct.class);
            query.setParameter("userId", user_id);
            orderProducts = query.list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find OrderProduct by userId.", e);
        }

        return orderProducts;
    }
}
