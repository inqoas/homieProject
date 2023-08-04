const user_jwt = localStorage.getItem('user_jwt');

const [header,payload,signature] = user_jwt.split(".");
// 使用 atob 解碼 Base64 字串，得到二進制數據
const decoded_payload = atob(payload);

// 將二進制數據轉換為 UTF-8 字串
const utf8_payload = decodeURIComponent(Array.from(decoded_payload).map(char => '%' + ('00' + char.charCodeAt(0).toString(16)).slice(-2)).join(''));

// 將 UTF-8 字串解析為 JSON 物件
const user = JSON.parse(utf8_payload);
const user_div =  document.querySelector(".onhover-div-login");
const username_div = document.querySelector(".username");
const user_id = user.user_id;
const user_status = user.user_status;
const user_name = user.user_name;

//網頁load時匯入資料
window.addEventListener("load", function(){
    if(user_jwt == null){
        alert("請重新登入");
        location = "../front-end/login.html";
    }

    //獲得賣家資訊
    fetch("../sellerProfile/getSellerProfile",{
        method:"GET",
        headers:{
            "Authorization": "Bearer " + user_jwt
        }
    }).then(resp => {
        return resp.json();
    }).then(
        body =>{
            document.querySelector("#bank_holder_name").value = body.bankHolderName;
            document.querySelector("#bank_code").value = body.bankCode;
            document.querySelector("#bank_account").value = body.bankAccount;
            document.querySelector(".pcrc_img").src = "data:image/png;base64," + body.sellerPcrcBase64;
        },
        error => {

        }
    )

    //獲得會員資訊
    fetch("../user-profile", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + user_jwt
        }
    }).then(
        function(resp){
            return resp.json();
        }
    ) 
    .then(
        function(body){
            if(body == null || body.success == false){
                localStorage.removeItem('user_jwt');
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
            document.querySelector(".dashboard-profile-id").innerHTML = body.user_id;
            document.querySelector(".dashboard-profile-account").innerHTML = body.user_account;
            document.querySelector(".dashboard-profile-password").value = body.user_password; 
            document.querySelector(".dashboard-profile-name").innerHTML = body.user_name;
            document.querySelector(".dashboard-profile-phone").innerHTML = body.user_phone;
            document.querySelector(".dashboard-profile-ic").innerHTML = body.user_ic;
            document.querySelector(".dashboard-profile-address").innerHTML = body.user_address;
            document.querySelector(".dashboard-profile-gender").innerHTML = gender;
            document.querySelector(".dashboard-profile-birth").innerHTML = body.user_birth;
            document.querySelector(".dashboard-profile-gc").innerHTML = body.garbage_coin;
            document.querySelector("#pname").value = body.user_name;
            document.querySelector("#pphone").value = body.user_phone;
            document.querySelector(".profile-name-name").value = body.user_name;
            document.querySelector(".profile-name-account").innerHTML = body.user_account;
            const selectedCity = body.user_address.substring(0, 3);
            const floatingSelect1 = document.querySelector("#floatingSelect1");
            for(let i = 0; i < floatingSelect1.options.length; i++){
                if(floatingSelect1.options[i].value == selectedCity){
                    floatingSelect1.options[i].selected = true;
                    break;
                }
            }
            document.querySelector("#paddress").value = body.user_address.substring(3);
            if(body.seller_identity == 0){
                document.querySelector("#bankName").classList.add("display")
                document.querySelector("#bankCode").classList.add("display")
                document.querySelector("#bankAccount").classList.add("display")
                document.querySelector("#pcrcImg").classList.add("display")
            }
            if(body.seller_identity == 1){
                document.querySelector(".apply-business").classList.add("display")
                document.querySelector(".sellerTable").insertAdjacentHTML('beforebegin', "<div style='font-weight: bolder; color: #274C77; font-size: 20px'>商家申請中</div>");
            }
            if(body.seller_identity == 2){
                document.querySelector(".apply-business").classList.add("display")
            }
            }
            
        },
        function(error){

        }
    )
})

//修改個人檔案
document.querySelector("#psave").addEventListener("click", function(){
    const pid = document.querySelector(".dashboard-profile-id")
    const pname = document.querySelector("#pname");
    const pphone = document.querySelector("#pphone");
    const paddress = document.querySelector("#floatingSelect1").value + document.querySelector("#paddress").value;
    
    fetch("../changeUserInfo",{
        method:"POST",
        headers:{
            "Content-Type" : "application/json",
            "Authorization": "Bearer " + user_jwt
        },
        body: JSON.stringify({
            user_id: pid.innerText,
            user_name: pname.value,
            user_phone: pphone.value,
            user_address: paddress
        })
    }).then(
        function(resp){
            return resp.json();
        }
    ).then(
        function(body){
            alert(body.message);
            if(body.message == "會員驗證錯誤請重新登入"){
                localStorage.removeItem('user_jwt');
                location = "../front-end/login.html";
            }
            if(body.success == false){

            }else{
                location.reload();
            }
            
            
        },
        function(error){

        }
    )
})

//修改大頭貼
document.querySelector(".headimg").addEventListener("change", function () {
    const file = this.files[0];
  
    // 如果未選擇檔案或選擇的不是圖片
    if (!file || !file.type.startsWith("image/")) {
      // 處理空值，例如不上傳圖片的情況
      console.log("未選擇檔案或選擇的不是圖片");
      return;
    }

    // 創建一個 FormData 對象
    const formData = new FormData();
    formData.append('file', file);
    
    fetch('../changeUserPic', {
      method: 'POST',
      headers:{
        "Authorization": "Bearer " + user_jwt
    },
      body: formData
    })
      .then(response => response.json())
      .then(body => {
        if(body.message != null){
            alert(body.message);
        }
        if(body.success == false && body.message == "帳號驗證錯誤，請重新登入"){
            localStorage.removeItem("user_jwt");
            location = "../front-end/login.html";
        }

      })
      .catch(error => {
        // 這裡處理錯誤情況
        console.error('Error:', error);
      });
  });
  
  function showProducts(){
    document.querySelector("#collection-product").classList.toggle("collection")
    document.querySelector("#collection-service").classList.toggle("collection")
    
    fetch("", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + user_jwt
        }
    }).then(resp => {
        return resp.json();
    }).then(
        body => {

        },
        error => {

        }
    )
  }

  function showServices(){
    document.querySelector("#collection-product").classList.toggle("collection")
    document.querySelector("#collection-service").classList.toggle("collection")

    fetch("", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + user_jwt
        }
    }).then(resp => {
        return resp.json();
    }).then(
        body => {

        },
        error => {
            
        }
    )

  }