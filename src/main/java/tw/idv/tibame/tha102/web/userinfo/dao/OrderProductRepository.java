package tw.idv.tibame.tha102.web.userinfo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // 添加这行导入
import org.springframework.stereotype.Repository;

import tw.idv.tibame.tha102.web.orderproduct.vo.OrderProduct;
import tw.idv.tibame.tha102.web.orderProductDetail.vo.OrderProductDetail; // 添加这行导入

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

	@Query(value = "SELECT op.order_product_id, op.product_placement_time , op.product_status, opd.product_name, opd.product_quantity, opd.product_price FROM order_product op " + 
	           "JOIN order_product_detail opd ON op.order_product_id = opd.order_product_id " +
	           "WHERE op.user_id = :userId " +
	           "ORDER BY op.order_product_id DESC"
	           ,nativeQuery = true)
	List<Object> selectAllOrderProductAndDetailByUserId(Integer userId);


	@Query(value = "SELECT op.order_product_id, op.delivery_time , op.product_status, opd.product_name, opd.product_quantity, opd.product_price FROM order_product op "
			+ "JOIN order_product_detail opd ON op.order_product_id = opd.order_product_id "
			+ "WHERE op.user_id = :userId AND op.product_status = :productStatus "
			+ "ORDER BY op.order_product_id DESC", nativeQuery = true)
	List<Object> selectAllOrderProductAndDetailByUserIdAndProductStatus(Integer userId, Integer productStatus);
	
	
}
