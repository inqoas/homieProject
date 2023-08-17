$(document).ready(function() {
    $('form').on('submit', function(event) {
        event.preventDefault(); // 阻止默認的表單提交行為

        // 獲取用戶輸入的email
        var email = $('#email').val();

        // 發送AJAX請求到/forgot-password端點
        $.ajax({
            url: '../emps/forgot-password',
            type: 'POST',
            data: { email: email },
            success: function() {
                // 請求成功時的操作，例如顯示成功消息
                alert('重設密碼連結已發送到你的電子郵件地址');
            },
            error: function() {
                // 請求失敗時的操作，例如顯示錯誤消息
                alert('無法發送重設密碼連結，請稍後再試');
            }
        });
    });
});