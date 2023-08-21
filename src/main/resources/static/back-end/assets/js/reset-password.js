$(document).ready(function() {
    $('form').submit(function(event) {
        event.preventDefault(); // 防止表單的默認提交行為

        const newPassword = $('#newPassword').val();
        const confirmNewPassword = $('#confirmNewPassword').val();

        if (newPassword !== confirmNewPassword) {
            alert("新密碼和確認密碼不匹配！");
            return;
        }

        const urlParams = new URLSearchParams(window.location.search);
        const resetToken = urlParams.get('token');

        const url = '../emps/reset-password?resetToken=' + encodeURIComponent(resetToken) + '&newPassword=' + encodeURIComponent(newPassword);

        $.ajax({
            type: 'POST',
            url: url,
            success: function(data) {
                alert("密碼已成功重置！");
                window.location.href = 'login.html';
            },
            error: function(error) {
                console.error('Error:', error);
                alert("密碼重置時出錯，請稍後再試！");
            }
        });
    });
});
