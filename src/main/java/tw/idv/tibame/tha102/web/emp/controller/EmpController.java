package tw.idv.tibame.tha102.web.emp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.idv.tibame.tha102.web.emp.service.EmailService;
import tw.idv.tibame.tha102.web.emp.service.EmpService;
import tw.idv.tibame.tha102.web.emp.vo.Emp;
import tw.idv.tibame.tha102.web.emp.vo.LoginRequest;

import java.util.Optional;

@RestController
@RequestMapping("/emps")
public class EmpController {

    private final EmpService empService;

    private EmailService emailService;

    @Autowired
    public EmpController(EmpService empService, EmailService emailService) {
        this.empService = empService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Emp> emp = empService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (emp.isPresent()) {
            return new ResponseEntity<>(emp.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("無效的信箱或密碼", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String resetToken = empService.createResetToken(email);
        if (resetToken != null) {
            String resetUrl = "http://localhost:8080/homieProject/back-end/reset-password.html?token=" + resetToken;
            String htmlMessage = "<p>點擊 <a href=\"" + resetUrl + "\">here</a> 來重設密碼</p>";
            emailService.sendHtmlMessage(email, "Homie員工後台重設密碼", htmlMessage);
            return new ResponseEntity<>("重設 token 已寄到信箱", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("寄送重設 token 失敗", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String resetToken, @RequestParam String newPassword) {
        boolean success = empService.resetPassword(resetToken, newPassword);
        if (success) {
            return new ResponseEntity<>("密碼重設成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("密碼重設失敗", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate(); // 使session無效
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
}

