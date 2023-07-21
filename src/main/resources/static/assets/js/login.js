const account = document.querySelector("#email");
const password = document.querySelector("#password");
document.querySelector(".login-account").addEventListener("click", function(){
    if(!account.value){
        // document.querySelector(".login-error-message").innerHTML = "登入失敗，請稍後再試或使用其他登入方法";
        alert("帳號或密碼錯誤");
        return;
    }
    if(!password.value){
        // document.querySelector(".login-error-message").innerHTML = "登入失敗，請稍後再試或使用其他登入方法";
        alert("帳號或密碼錯誤");
        return;
    }
    fetch("login", {
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
            //處理回應
        },
        function(error) {
            // 在此處理錯誤情況
          }
      )

      
      
})