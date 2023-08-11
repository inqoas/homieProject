package tw.idv.tibame.tha102.web.seller.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.seller.vo.Seller;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer> {
    Seller findByUserId(Integer userId);
}
