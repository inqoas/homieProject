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
                localStorage.setItem('user_jwt', body.user_jwt);
            }
        },
        function(error) {
            // 在此處理錯誤情況
          }
      )

      
      
})