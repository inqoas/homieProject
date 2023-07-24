const account = document.querySelector("#email");
const password = document.querySelector("#password");
document.querySelector("#login-button").addEventListener("click", function(){

    fetch("../login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            user_account : account.value,
            user_password : password.value
        })
    }).then(function(resp) {
        return resp.json();
      })
      .then(
        function(body){
            document.querySelector(".login-error-message").innerHTML = body.message;
            if(body.success === true){
                location = "../front-end/user-dashboard.html";
                sessionStorage.setItem('user_id', body.user_id);
                sessionStorage.setItem('user_name', body.user_name);
                sessionStorage.setItem('user_status', body.user_status);
                sessionStorage.setItem('seller_identity', body.seller_identity);
            }
        },
        function(error) {
            // 在此處理錯誤情況
          }
      )

      
      
})