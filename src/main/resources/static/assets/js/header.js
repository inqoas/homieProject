const user_jwt2 = localStorage.getItem('user_jwt');

window.addEventListener("load", function(){
  if(user_jwt2 != null){
    fetch("../header", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + user_jwt2
      }
    }).then(function(resp) {
      return resp.json();
    }).then(function(body) {
      if (body === null || body.success == false) {
        localStorage.removeItem("user_jwt");
        alert("請重新登入")
        window.location.replace("../front-end/login.html");
      }
      const user_id = body.user_id;
      const seller_identity = body.seller_identity;
      const user_name = body.user_name;
      const success = body.success;                //front-end/user-dashboard.html
      const user_div = document.querySelector(".onhover-div-login")
      if(user_id != null && seller_identity != null && user_name != null && success == true){
        user_div.innerHTML = `
                            <ul class="logined-box">
                                <li class="product-box-contain">
                                    <i></i>
                                    <a href="../front-end/user-dashboard.html">會員中心</a>
                                </li>
                                <br>
                                <li class="product-box-contain">
                                    <a href="#" class="log-out">登出</a>
                                </li>
                            </ul>
                            `;
        document.querySelector(".username").innerHTML = user_name;
        
    }
    }).catch(function(error) {
      // 在此處理錯誤情況
    }); 
  }
})

$(document).on("click", ".log-out",function () {
    fetch("../logout", {
      method: "POST"
    })
      .then(
        function (body) {
          localStorage.removeItem('user_jwt');
          location = "../front-end/index.html";
        },
        function (error) {
          // 在此處理錯誤情況
        }
      );
  });
  
