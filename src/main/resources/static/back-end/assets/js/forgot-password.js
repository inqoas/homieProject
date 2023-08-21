$(document).ready(function() {
    $('form').on('submit', function(event) {
        event.preventDefault(); 

        var email = $('#email').val();

        $.ajax({
            url: '../emps/forgot-password',
            type: 'POST',
            data: { email: email },
            success: function() {
                alert('重設密碼連結已發送到你的電子郵件地址');
            },
            error: function() {
                alert('無法發送重設密碼連結，請稍後再試');
            }
        });
    });
});