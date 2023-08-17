const urlParams = new URLSearchParams(window.location.search);
const userId = urlParams.get("user_id");
console.log(userId);

document.addEventListener("DOMContentLoaded", function() {
    try {
        const getUserProfileAPI = "../userinfo/find-by-id";
        const getSellerProfileAPI = `../sellers/by-user/${userId}`;
        const getPcrcImageAPI = `http://localhost:8080/homieProject/sellers/pcrc`;

        const imgElement = document.getElementById('pcrcImage');
        imgElement.src = `${getPcrcImageAPI}?user_id=${userId}`;

        $.ajax({
            url: `${getUserProfileAPI}?user_id=${userId}`,
            type: 'GET',
            dataType: "json",
            success: function(user) {
                console.log(user);

                const userProfileContainer = document.getElementById("userProfileContainer");
                userProfileContainer.querySelector('#nameInput').textContent = user[0].user_name;
                userProfileContainer.querySelector('#idInput').textContent = user[0].user_id;

                $.ajax({
                    url: getSellerProfileAPI,
                    type: 'GET',
                    dataType: "json",
                    success: function(seller) {
                        console.log(seller);

                        userProfileContainer.querySelector('#bankCodeInput').textContent = seller.bankCode;
                        userProfileContainer.querySelector('#holderInput').textContent = seller.bankHolderName;
                        userProfileContainer.querySelector('#bankAccountInput').textContent = seller.bankAccount;
                    },
                    error: function(error) {
                        console.error("無法取得Seller資料：", error);
                    }
                });
            },
            error: function(error) {
                console.error("無法取得Userinfo：", error);
            }
        });
    } catch (error) {
        console.error("無法取得會員資料：", error);
    }

    document.getElementById("backButton").addEventListener("click", function() {
        history.back();
    });

    const updateStatusPassAPI = "../userinfo/update-status-pass";
    document.getElementById("sellerPassButton").addEventListener("click", function() {
        console.log("點擊了通過按鈕，試圖更改用戶狀態");
        console.log(userId);

        $.ajax({
            url: `${updateStatusPassAPI}?user_id=${userId}`,
            type: 'POST',
            success: function(response) {
                console.log(response.message);
                alert(response.message);
                window.location.href = 'all-users.html';
            }
        });
    });

    const updateStatusFailAPI = "../userinfo/update-status-fail";
    document.getElementById("sellerFailButton").addEventListener("click", function() {
        console.log("點擊了通過按鈕，試圖更改用戶狀態");
        console.log(userId);

        $.ajax({
            url: `${updateStatusFailAPI}?user_id=${userId}`,
            type: 'POST',
            success: function(response) {
                console.log(response.message);
                alert(response.message);
                window.location.href = 'all-users.html';
            }
        });
    });

    document.getElementById('pcrcImage').addEventListener('click', function() {
        const imgSrc = this.src;
        document.getElementById('modalImage').src = imgSrc;
        const imageModal = new bootstrap.Modal(document.getElementById('imageModal'));
        imageModal.show();
    });
});
