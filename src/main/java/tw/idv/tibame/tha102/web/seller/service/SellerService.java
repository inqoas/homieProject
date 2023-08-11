package tw.idv.tibame.tha102.web.seller.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.tibame.tha102.web.seller.repo.SellerRepo;
import tw.idv.tibame.tha102.web.seller.vo.Seller;

@Service
public class SellerService {
    private final SellerRepo sellerRepo;

    @Autowired
    public SellerService(SellerRepo sellerRepo){
        this.sellerRepo = sellerRepo;
    }

    // 新增
    public Seller createSeller(Seller seller){
        return sellerRepo.save(seller);
    }

    // 更新
    public Seller updateSeller(Integer id, Seller updateSeller){
        return  sellerRepo.findById(id).map(existingSeller -> {
            existingSeller.setSellerPcrc(updateSeller.getSellerPcrc());
            existingSeller.setBankCode(updateSeller.getBankCode());
            existingSeller.setBankAccount(updateSeller.getBankAccount());
            existingSeller.setBankHolderName(updateSeller.getBankHolderName());
            existingSeller.setTotalReviewCount(updateSeller.getTotalReviewCount());
            existingSeller.setTotalReviewStars(updateSeller.getTotalReviewStars());
            return sellerRepo.save(existingSeller);
        }).orElseThrow(() -> new EntityNotFoundException("Seller with id " + id + " not found."));
    }

    // 依userId查詢
    public Seller getSellerByUserId(Integer userId){
        return sellerRepo.findByUserId(userId);
    }

    public byte[] getPcrcByUserId(Integer userId) {
        Seller seller = sellerRepo.findByUserId(userId);
        if (seller != null) {
            return seller.getSellerPcrc();
        }
        return null;
    }
}
