package tw.idv.tibame.tha102.web.orderproduct.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;

public class OrderProductDaoImpl implements OrderProductDao {

    private final SessionFactory sessionFactory;

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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public List<OrderProduct> getAll() {
        List<OrderProduct> orderProducts = null;

        try (Session session = sessionFactory.openSession()) {
            Query<OrderProduct> query = session.createQuery("FROM OrderProduct", OrderProduct.class);
            orderProducts = query.list();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }

        return orderProducts;
    }   
}
