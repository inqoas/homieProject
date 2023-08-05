const statusMapping = {
    0: "正常",
    1: "停權",
    2: "未審核",
    3: "註銷",
};

function getStatusText(statusCode) {
    return statusMapping[statusCode] || "未知狀態";
}

const sellerMapping = {
    0: "不是",
    1: "是"
};

function getSellerText(sellerCode){
    return sellerMapping[sellerCode] || "未知選項"
}

const genderMapping = {
    0: "男",
    1: "女"
};

function getGenderText(genderCode){
    return genderMapping[genderCode] || "未知性別"
}


document.addEventListener("DOMContentLoaded", function () {
    try {
        const urlParams = new URLSearchParams(window.location.search);
        const userId = urlParams.get("user_id");
        console.log(userId);

        const getUserProfileAPI = "../userinfo/find-by-id";
        const getUserProfileImageAPI = `http://localhost:8080/homieProject/userinfo/user-info-find-img?user_id=${userId}`;


        // 發送 GET 請求取得會員資料
        $.ajax({
            url: `${getUserProfileAPI}?user_id=${userId}`,
            type: 'GET',
            dataType: "json",
            success: function(data) {
                console.log(data);

                const profileImage = document.getElementById("profileImage");
                profileImage.src = getUserProfileImageAPI;

                const statusText = getStatusText(data[0].user_status);
                const sellerText = getSellerText(data[0].seller_identity);
                const genderText = getGenderText(data[0].user_gender);
                const userProfileContainer = document.getElementById("userProfileContainer");
                userProfileContainer.querySelector('#nameInput').textContent = data[0].user_name;
                userProfileContainer.querySelector('#idInput').textContent = data[0].user_id;
                userProfileContainer.querySelector('#accountInput').textContent = data[0].user_account;
                userProfileContainer.querySelector('#phoneInput').textContent = data[0].user_phone;
                userProfileContainer.querySelector('#genderInput').textContent = genderText;
                userProfileContainer.querySelector('#addressInput').textContent = data[0].user_address;
                userProfileContainer.querySelector('#birthInput').textContent = data[0].user_birth;
                userProfileContainer.querySelector('#icInput').textContent = data[0].user_ic;
                userProfileContainer.querySelector('#statusInput').textContent = statusText;
                userProfileContainer.querySelector('#coinInput').textContent = data[0].garbage_coin;
                userProfileContainer.querySelector('#sellerInput').textContent = sellerText;
                userProfileContainer.querySelector('#user-detail-name').textContent = data[0].user_name;
                userProfileContainer.querySelector('#user-detail-email').textContent = data[0].user_account;



            },
            error: function (error) {
                console.error("無法取得會員資料：", error);
            }
        });
    } catch (error) {
        console.error("無法取得會員資料：", error);
    }

    const backButton = document.getElementById("backButton");
    backButton.addEventListener("click", function () {
        history.back();
    });
    

});
