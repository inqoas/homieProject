window.addEventListener("load", function(){
    // fetch("../pills-dashboard", {
    //     method: "POST"
    // }).then(
    //     function(resp){
    //         return resp.json();
    //     }
    // )
    // .then(
    //     function(body){

    //     },
    //     function(error){

    //     }
    // )
    // if(sessionStorage.getItem(user_id) == null || sessionStorage.getItem(user_name) == null || sessionStorage.getItem(user_status) == null){
    //     alert("請重新登入")
    //     location = "../front-end/login.html"
    // }
    fetch("../user-profile", {
        method: "POST"
    }).then(
        function(resp){
            return resp.json();
        }
    )
    .then(
        function(body){
            if(body == null || body.success == false){
                sessionStorage.removeItem('user_id');
                sessionStorage.removeItem('user_name');
                sessionStorage.removeItem('user_status');
                alert("請重新登入")
                location = "../front-end/login.html"
            }
            if(body.success == true){
                let gender = null;
            if(body.user_gender == 0){
                gender = "男"
            }else if(body.user_gender == 1){
                gender = "女";
            }
            document.querySelector(".dashboard-detail-user-account").innerHTML = body.user_account;
            document.querySelector(".dashboard-detail-user-name").innerHTML = body.user_name;
            document.querySelector(".dashboard-detail-user-phone").innerHTML = body.user_phone;
            document.querySelector(".dashboard-detail-user-address").innerHTML = body.user_address;
            document.querySelector(".dashboard-detail-user-birth").innerHTML = body.user_birth;
            document.querySelector(".update_img").src = "data:image/png;base64," + body.user_pic_base64;
            document.querySelector(".dashboard-profile-account").innerHTML = body.user_account;
            document.querySelector(".dashboard-profile-password").value = body.user_password; 
            document.querySelector(".dashboard-profile-name").innerHTML = body.user_name;
            document.querySelector(".dashboard-profile-phone").innerHTML = body.user_phone;
            document.querySelector(".dashboard-profile-ic").innerHTML = body.user_ic;
            document.querySelector(".dashboard-profile-address").innerHTML = body.user_address;
            document.querySelector(".dashboard-profile-gender").innerHTML = gender;
            document.querySelector(".dashboard-profile-birth").innerHTML = body.user_birth;
            document.querySelector(".dashboard-profile-gc").innerHTML = body.garbage_coin;
            }
            
        },
        function(error){

        }
    )
})