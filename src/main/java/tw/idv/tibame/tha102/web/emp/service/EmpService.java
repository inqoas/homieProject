package tw.idv.tibame.tha102.web.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.tibame.tha102.web.emp.repo.EmpRepo;
import tw.idv.tibame.tha102.web.emp.vo.Emp;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmpService {

    private final EmpRepo empRepo;

    @Autowired
    public EmpService(EmpRepo empRepo) {
        this.empRepo = empRepo;
    }

    public Optional<Emp> login(String email, String password) {
        Optional<Emp> emp = empRepo.findByEmpEmail(email);
        if (emp.isPresent() && checkPassword(password, emp.get().getEmpPassword())) {
            return emp;
        }
        return Optional.empty();
    }

    public Optional<Emp> findByResetToken(String resetToken) {
        return empRepo.findByResetToken(resetToken);
    }

    // 修改密碼邏輯
    public boolean resetPassword(String resetToken, String newPassword) {
        Optional<Emp> emp = findByResetToken(resetToken);
        if (emp.isPresent()) {
            Emp employee = emp.get();
            employee.setEmpPassword(newPassword); // 設置新密碼，這裡是明文
            employee.setResetToken(null); // 清除重置令牌
            employee.setResetTokenExpiry(null); // 清除令牌到期時間
            empRepo.save(employee);
            return true;
        }
        return false;
    }

    // 檢查密碼是否與數據庫中的密碼匹配，這裡直接使用明文比對
    private boolean checkPassword(String rawPassword, String storedPassword) {
        return rawPassword.equals(storedPassword);
    }

    public String createResetToken(String email) {
        // 查找電子郵件對應的員工
        Optional<Emp> empOptional = empRepo.findByEmpEmail(email);
        if (!empOptional.isPresent()) {
            return null; // 若找不到員工則返回null
        }

        Emp employee = empOptional.get();

        String resetToken = UUID.randomUUID().toString();

        LocalDateTime resetTokenExpiry = LocalDateTime.now().plusHours(1);

        // 更新員工記錄
        employee.setResetToken(resetToken);
        employee.setResetTokenExpiry(resetTokenExpiry);
        empRepo.save(employee);

        return resetToken; // 返回令牌
    }

}
