document.addEventListener("DOMContentLoaded", function () {
    try {
        const urlParams = new URLSearchParams(window.location.search);
        const userId = urlParams.get("user_id");
        console.log(userId);

        const getUserProfileAPI = "../userinfo/findbyid";

        // 發送 GET 請求取得會員資料
        $.ajax({
            url: `${getUserProfileAPI}?user_id=${userId}`,
            type: 'GET',
            dataType: "json",
            success: function(data) {
                console.log(data);
                const userProfileContainer = document.getElementById("userProfileContainer");
                userProfileContainer.querySelector('#nameInput').value = data.user_name;
                userProfileContainer.querySelector('#idInput').value = data.user_id;
                userProfileContainer.querySelector('[placeholder="輸入密碼"]').value = data.user_password;
                userProfileContainer.querySelector('[placeholder="輸入帳號"]').value = data.user_account;
                userProfileContainer.querySelector('[placeholder="輸入手機號碼"]').value = data.user_phone;
                userProfileContainer.querySelector('[placeholder="輸入性別"]').value = data.user_gender;
                userProfileContainer.querySelector('[placeholder="輸入地址"]').value = data.user_address;
                userProfileContainer.querySelector('[placeholder="輸入生日"]').value = data.user_birth;
                userProfileContainer.querySelector('[placeholder="輸入分證字號"]').value = data.user_ic;
                userProfileContainer.querySelector('[placeholder="輸入狀態"]').value = data.user_status;
                userProfileContainer.querySelector('[placeholder="輸入GC"]').value = data.garbage_coin;
                userProfileContainer.querySelector('[placeholder="輸入是否為賣家"]').value = data.seller_identity;
                userProfileContainer.querySelector('#user-detail-name').textContent = data.user_name;
                userProfileContainer.querySelector('#user-detail-email').textContent = data.user_account;

                // ...其他表單欄位的填入...
            },
            error: function (error) {
                console.error("無法取得會員資料：", error);
            }
        });
    } catch (error) {
        console.error("無法取得會員資料：", error);
    }
});
