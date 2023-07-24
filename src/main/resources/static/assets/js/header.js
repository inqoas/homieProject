const user_div =  document.querySelector(".onhover-div-login");
const username_div = document.querySelector(".username");
const user_id = sessionStorage.getItem("user_id");
const user_status = sessionStorage.getItem("user_status");
const user_name = sessionStorage.getItem("user_name");
window.addEventListener("load", function(){
    if(user_id != null && user_status != null && user_name != null){
        user_div.innerHTML = `
                            <ul class="logined-box">
                                <li class="product-box-contain">
                                    <i></i>
                                    <a href="user-dashboard.html">會員中心</a>
                                </li>
                                <br>
                                <li class="product-box-contain">
                                    <a href="#" class="log-out">登出</a>
                                </li>
                            </ul>
                            `;

        username_div.innerHTML = user_name;
        
    }
})

$(document).on("click", ".log-out",function () {
    fetch("../logout", {
      method: "POST"
    })
      .then(
        function (body) {
          sessionStorage.removeItem('user_id');
          sessionStorage.removeItem('user_name');
          sessionStorage.removeItem('user_status');
          location = "../front-end/index.html";
        },
        function (error) {
          // 在此處理錯誤情況
        }
      );
  });
  
