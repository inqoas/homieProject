$(document).ready(function() {
    $('form').submit(function(event) {
        event.preventDefault(); // 防止表單的默認提交行為

        const newPassword = $('#newPassword').val();
        const confirmNewPassword = $('#confirmNewPassword').val();

        // 檢查新密碼和確認密碼是否匹配
        if (newPassword !== confirmNewPassword) {
            alert("新密碼和確認密碼不匹配！");
            return;
        }

        // 提取resetToken。您可能需要根據您的情況調整此部分。
        const urlParams = new URLSearchParams(window.location.search);
        const resetToken = urlParams.get('token');

        // 構造帶有參數的URL
        const url = '../emps/reset-password?resetToken=' + encodeURIComponent(resetToken) + '&newPassword=' + encodeURIComponent(newPassword);

        // 使用jQuery的AJAX方法發送POST請求到服務器
        $.ajax({
            type: 'POST',
            url: url,
            success: function(data) {
                alert("密碼已成功重置！");
                // 可以導航到登錄頁面或其他操作
                window.location.href = 'login.html';
            },
            error: function(error) {
                console.error('Error:', error);
                alert("密碼重置時出錯，請稍後再試！");
            }
        });
    });
});
