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
            if(body.sellerPcrcBase64 != null){
                document.querySelector(".pcrc_img").src = "data:image/png;base64," + body.sellerPcrcBase64;
            }else{
                document.querySelector(".pcrc_img").classList.add("display")
            }
           
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
            if(body.user_pic_base64!=null){
                document.querySelector(".update_img").src = "data:image/png;base64," + body.user_pic_base64;
            }else{
                document.querySelector(".update_img").src = "../assets/images/inner-page/user/1.jpg";
            }
           
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
            document.querySelector(".profile-name-name").innerHTML = body.user_name;
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
            if(body.user_status == 2 && body.seller_identity == 0){
                document.querySelector(".apply-business").classList.add("display")
                document.querySelector("#bankName").classList.remove("display")
                document.querySelector("#bankCode").classList.remove("display")
                document.querySelector("#bankAccount").classList.remove("display")
                document.querySelector("#pcrcImg").classList.remove("display")
                document.querySelector(".sellerTable").insertAdjacentHTML('beforebegin', "<div style='font-weight: bolder; color: #274C77; font-size: 20px'>商家申請中</div>");
            }
            if(body.user_status == 0 && body.seller_identity == 0){
                document.querySelector("#bankName").classList.add("display")
                document.querySelector("#bankCode").classList.add("display")
                document.querySelector("#bankAccount").classList.add("display")
                document.querySelector("#pcrcImg").classList.add("display")
            }
            if(body.seller_identity == 1){
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
      alert("未選擇檔案或選擇的不是圖片");
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
    document.querySelector("#collection-product").classList.add("collection");
    document.querySelector("#collection-service").classList.remove("collection");
    
    fetch("../collection/getAllProductCollection", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + user_jwt
        }
    }).then(resp => {
        return resp.json();
    }).then(
        body => {
            let productId = 0;
            let productCollectionHTML = "";
                body.forEach(collection =>{
                    productId = collection[0];
                    let productName = collection[1];
                    let productPrice = collection[2];
                    let productCategory = collection[3];
                    if(productCategory == 0){
                        productCategory = "清潔劑"
                    }else if(productCategory == 1){
                        productCategory = "洗衣精/粉"
                    }else if(productCategory == 2){
                        productCategory = "掃除用品"
                    }else if(productCategory == 3){
                        productCategory = "洗碗精/粉"
                    }
                    productCollectionHTML += `
                                            <div class="col-xxl-3 col-lg-6 col-md-4 col-sm-6">
                                            <div class="product-box-3 theme-bg-white h-90">
                                                <div class="product-header">
                                                    <div class="product-image">
                                                        <a href="product-left-thumbnail.html">
                                                            <img src="http://localhost:8080/homieProject/collection/getProductPic?productId=${productId}"
                                                                class="img-fluid blur-up lazyload" alt="">
                                                        </a>
    
                                                        <div class="product-header-top">
                                                            <button class="btn wishlist-button close_button" style="font-size : 12px; font-weight: bold;">
                                                                x
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
    
                                                <div class="product-footer">
                                                    <div class="product-detail">
                                                        <span class="span-name">${productCategory}</span>
                                                        <a href="product-left-thumbnail.html">
                                                            <h5 class="collectionName">${productName}</h5>
                                                        </a>
                                                        <p class="text-content mt-1 mb-2 product-content">Cheesy feet
                                                            cheesy grin brie. Mascarpone cheese and wine hard cheese the
                                                            big cheese everyone loves smelly cheese macaroni cheese
                                                            croque monsieur.</p>
                                                        <h5 class="price">
                                                            <span class="theme-color">${productPrice}元</span>
                                                        </h5>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>`                
            });
            document.querySelector("#myWishlist").innerHTML = productCollectionHTML;
        },
        error => {

        }
    )
  }

  function showServices(){
    document.querySelector("#collection-product").classList.remove("collection");
    document.querySelector("#collection-service").classList.add("collection");

    fetch("../collection/getAllServiceCollection", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + user_jwt
        }
    }).then(resp => {
        return resp.json();
    }).then(
        body => {
            let serviceId = 0;
            let serviceCollectionHTML = "";
            body.forEach(collection => {
                serviceId = collection[0];
                let serviceName = collection[1];
                let servicePrice = collection[2];
                let serviceCode = collection[3];
                if(serviceCode == 0){
                    serviceCode = "居家清潔";
                }else if(serviceCode == 1){
                    serviceCode = "冷氣清潔";
                }else if(serviceCode == 2){
                    serviceCode = "洗衣機清潔";
                }else if(serviceCode == 3){
                    serviceCode = "專業除蟎";
                }else if(serviceCode == 4){
                    serviceCode = "水塔清洗";
                }else if(serviceCode == 5){
                    serviceCode = "水管清洗";
                }
                serviceCollectionHTML += `
                                        <div class="col-xxl-3 col-lg-6 col-md-4 col-sm-6">
                                        <div class="product-box-3 theme-bg-white h-90">
                                            <div class="product-header">
                                                <div class="product-image">
                                                    <a href="product-left-thumbnail.html">
                                                        <img src="http://localhost:8080/homieProject/collection/getServicePic?serviceId=${serviceId}"
                                                            class="img-fluid blur-up lazyload" alt="">
                                                    </a>

                                                    <div class="product-header-top">
                                                        <button class="btn wishlist-button close_button" style="font-size : 12px; font-weight: bold;">
                                                            x
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="product-footer">
                                                <div class="product-detail">
                                                    <span class="span-name">${serviceCode}</span>
                                                    <a href="product-left-thumbnail.html">
                                                        <h5 class="collectionName">${serviceName}</h5>
                                                    </a>
                                                    <p class="text-content mt-1 mb-2 product-content">Cheesy feet
                                                        cheesy grin brie. Mascarpone cheese and wine hard cheese the
                                                        big cheese everyone loves smelly cheese macaroni cheese
                                                        croque monsieur.</p>
                                                    <h5 class="price">
                                                        <span class="theme-color">${servicePrice}元</span>
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>`    
            })
            document.querySelector("#myWishlist").innerHTML = serviceCollectionHTML;
        },
        error => {
            
        }
    )

  }

  $(document).on("click",".close_button",function(){
    let serviceName = $(this).closest(".product-box-3").find(".collectionName").text();
    let serviceCode = $(this).closest(".product-box-3").find(".span-name").text();
    console.log(serviceName);
    console.log(serviceCode);
    let controller = "";
    if(serviceCode == "居家清潔" || serviceCode == "冷氣清潔" || serviceCode == "洗衣機清潔" || serviceCode == "專業除蟎" || serviceCode == "水塔清洗" || serviceCode == "水管清洗"){
        controller = "../collection/deleteServiceCollection";
    }else{
        controller = "../collection/deleteProductCollection";
    }
    fetch(controller,{
        method:"DELETE",
        headers: {
            "Authorization": "Bearer " + user_jwt,
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            "collectionName": serviceName
        })
    }).then(resp =>{
        return resp.json();
    }).then(
        body =>{
            if(body.message != null){
                alert(body.message);
            }
            if(body.success == false && body.message == "驗證失敗，請重新登入"){
                localStorage.removeItem("user_jwt");
                location = "../front-end/login.html";
            }
        },
        error =>{

        }
    )
  })

  $(".pills-order-button").on("click", function(){
    fetch("../OrderProduct/getOrderProductPill", {
        method: "GET",
        headers:{
            "Authorization": "Bearer " + user_jwt,
        }
    }).then(resp =>{
        return resp.json();
    }).then(
        body =>{
            let orderHTML = "";
            body.forEach(collection =>{
                let message = collection.message;
                let productId = collection.productId;
                let productName = collection.productName;
                let productPlacementTime = collection.productPlacementTime;
                let productPrice = collection.productPrice;
                let productQuantity = collection.productQuantity;
                let productStatus = collection.productStatus;
                let success = collection.success;
                let dateTime = new Date(productPlacementTime);
                let productPlacementDate = dateTime.toISOString().split("T")[0];

                if(success == false && message == "驗證失敗，請重新登入"){
                    alert("驗證失敗，請重新登入");
                    localStorage.removeItem("user_jwt");
                    location = "../front-end/login.html";
                }
                let button = "";
                if(productStatus == 0){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 1){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 2){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }else if(productStatus == 3){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }
                orderHTML += `
                <tr>
                    <td class="order-td order-id col-2">${productId}</td>
                    <td class="order-td order-name text-truncate col-4">${productName}</td>
                    <td class="order-td order-count col-1">${productQuantity}</td>
                    <td class="order-td order-price col-1">${productPrice * productQuantity}元</td>
                    <td class="order-td order-delivery-time col-2">${productPlacementDate}</td>
                    <td class="order-td col-2">
                        ${button}
                    </td>
                </tr>`
            })
            $(".order-tbody").html(orderHTML);
        },
        error =>{

        }
    )
  });

  $(".order-all").on("click", function(){
    fetch("../OrderProduct/getOrderProductPill", {
        method: "GET",
        headers:{
            "Authorization": "Bearer " + user_jwt,
        }
    }).then(resp =>{
        return resp.json();
    }).then(
        body =>{
            let orderHTML = "";
            console.log(body)
            body.forEach(collection =>{
                let message = collection.message;
                let productId = collection.productId;
                let productName = collection.productName;
                let productPlacementTime = collection.productPlacementTime;
                let productPrice = collection.productPrice;
                let productQuantity = collection.productQuantity;
                let productStatus = collection.productStatus;
                let success = collection.success;
                let dateTime = new Date(productPlacementTime);
                let productPlacementDate = dateTime.toISOString().split("T")[0];

                if(success == false && message == "驗證失敗，請重新登入"){
                    alert("驗證失敗，請重新登入");
                    localStorage.removeItem("user_jwt");
                    location = "../front-end/login.html";
                }
                let button = "";
                if(productStatus == 0){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 1){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 2){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }else if(productStatus == 3){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }
                orderHTML += `
                <tr>
                    <td class="order-td order-id col-2">${productId}</td>
                    <td class="order-td order-name text-truncate col-4">${productName}</td>
                    <td class="order-td order-count col-1">${productQuantity}</td>
                    <td class="order-td order-price col-1">${productPrice * productQuantity}元</td>
                    <td class="order-td order-delivery-time col-2">${productPlacementDate}</td>
                    <td class="order-td col-2">
                        ${button}
                    </td>
                </tr>`
            })
            $(".order-tbody").html(orderHTML);
        },
        error =>{

        }
    )
  });

  $(".order-wait").on("click", function(){
    fetch("../OrderProduct/getOrderProductWait", {
        method: "GET",
        headers:{
            "Authorization": "Bearer " + user_jwt,
        }
    }).then(resp =>{
        return resp.json();
    }).then(
        body =>{
            console.log(body);
            let orderHTML = "";
            body.forEach(collection =>{
                let message = collection.message;
                let productId = collection.productId;
                let productName = collection.productName;
                let productPlacementTime = collection.productPlacementTime;
                let productPrice = collection.productPrice;
                let productQuantity = collection.productQuantity;
                let productStatus = collection.productStatus;
                let success = collection.success;
                let dateTime = new Date(productPlacementTime);
                let productPlacementDate = dateTime.toISOString().split("T")[0];

                if(success == false && message == "驗證失敗，請重新登入"){
                    alert("驗證失敗，請重新登入");
                    localStorage.removeItem("user_jwt");
                    location = "../front-end/login.html";
                }
                let button = "";
                if(productStatus == 0){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 1){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 2){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }else if(productStatus == 3){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }
                orderHTML += `
                <tr>
                    <td class="order-td order-id col-2">${productId}</td>
                    <td class="order-td order-name text-truncate col-4">${productName}</td>
                    <td class="order-td order-count col-1">${productQuantity}</td>
                    <td class="order-td order-price col-1">${productPrice * productQuantity}元</td>
                    <td class="order-td order-delivery-time col-2">${productPlacementDate}</td>
                    <td class="order-td col-2">
                        ${button}
                    </td>
                </tr>`
            })
            $(".order-tbody").html(orderHTML);
        },
        error =>{

        }
    )
  });

$(".order-end").on("click", function (){
    fetch("../OrderProduct/getOrderProductEnd", {
        method: "GET",
        headers:{
            "Authorization": "Bearer " + user_jwt,
        }
    }).then(resp =>{
        return resp.json();
    }).then(
        body =>{
            console.log(body);
            let orderHTML = "";
            body.forEach(collection =>{
                let message = collection.message;
                let productId = collection.productId;
                let productName = collection.productName;
                let productPlacementTime = collection.productPlacementTime;
                let productPrice = collection.productPrice;
                let productQuantity = collection.productQuantity;
                let productStatus = collection.productStatus;
                let success = collection.success;
                let dateTime = new Date(productPlacementTime);
                let productPlacementDate = dateTime.toISOString().split("T")[0];

                if(success == false && message == "驗證失敗，請重新登入"){
                    alert("驗證失敗，請重新登入");
                    localStorage.removeItem("user_jwt");
                    location = "../front-end/login.html";
                }
                let button = "";
                if(productStatus == 0){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 1){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 2){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }else if(productStatus == 3){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }
                orderHTML += `
                <tr>
                    <td class="order-td order-id col-2">${productId}</td>
                    <td class="order-td order-name text-truncate col-4">${productName}</td>
                    <td class="order-td order-count col-1">${productQuantity}</td>
                    <td class="order-td order-price col-1">${productPrice * productQuantity}元</td>
                    <td class="order-td order-delivery-time col-2">${productPlacementDate}</td>
                    <td class="order-td col-2">
                        ${button}
                    </td>
                </tr>`
            })
            $(".order-tbody").html(orderHTML);
        },
        error =>{

        }
    )
  });

  $(".order-return").on("click", function (){
    fetch("../OrderProduct/getOrderProductReturn", {
        method: "GET",
        headers:{
            "Authorization": "Bearer " + user_jwt,
        }
    }).then(resp =>{
        return resp.json();
    }).then(
        body =>{
            console.log(body);
            let orderHTML = "";
            body.forEach(collection =>{
                let message = collection.message;
                let productId = collection.productId;
                let productName = collection.productName;
                let productPlacementTime = collection.productPlacementTime;
                let productPrice = collection.productPrice;
                let productQuantity = collection.productQuantity;
                let productStatus = collection.productStatus;
                let success = collection.success;
                let dateTime = new Date(productPlacementTime);
                let productPlacementDate = dateTime.toISOString().split("T")[0];

                if(success == false && message == "驗證失敗，請重新登入"){
                    alert("驗證失敗，請重新登入");
                    localStorage.removeItem("user_jwt");
                    location = "../front-end/login.html";
                }
                let button = "";
                if(productStatus == 0){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 1){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button><button class="btn btn-danger order-cancel">取消訂單</button><button class="btn btn-success order-finish">完成訂單</button>'
                }else if(productStatus == 2){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }else if(productStatus == 3){
                    button = '<button class="btn btn-primary order-full">查看完整訂單</button>'
                }
                orderHTML += `
                <tr>
                    <td class="order-td order-id col-2">${productId}</td>
                    <td class="order-td order-name text-truncate col-4">${productName}</td>
                    <td class="order-td order-count col-1">${productQuantity}</td>
                    <td class="order-td order-price col-1">${productPrice * productQuantity}元</td>
                    <td class="order-td order-delivery-time col-2">${productPlacementDate}</td>
                    <td class="order-td col-2">
                        ${button}
                    </td>
                </tr>`
            })
            $(".order-tbody").html(orderHTML);
        },
        error =>{

        }
    )
  });

  $(document).on("click", ".order-full", function(){
    let orderId = $(this).closest("tr").find(".order-id").text().trim();
    location = "../front-end/order-product.html?orderProductId=" + orderId;
  })

  $(document).on("click", ".order-cancel", function(){
    let confirmed = confirm("確定取消訂單?");
    if(confirmed){
        let orderId = $(this).closest("tr").find(".order-id").text().trim();
        fetch("../OrderProduct/orderProductCancel",{
            method: "POST",
            headers: {
                "Authorization": "Bearer " + user_jwt,
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "orderId": orderId
            })
        }).then(resp => {
            return resp.text();
        }).then(
            body => {
                if(body == false){
                    alert("更改失敗")
                }else{
                    location.reload();
                }
                
            },
            error =>{
    
            }
        )
    }else{
        return;
    }
  })

  $(document).on("click", ".order-finish", function(){
    let confirmed = confirm("確定完成訂單?");
    if(confirmed){
        let orderId = $(this).closest("tr").find(".order-id").text().trim();
        fetch("../OrderProduct/orderProductFinish",{
            method: "POST",
            headers: {
                "Authorization": "Bearer " + user_jwt,
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "orderId": orderId
            })
        }).then(resp => {
            return resp.text();
        }).then(
            body => {
                if(body == false){
                    alert("更改失敗")
                }else{
                    location.reload();
                }
                
            },
            error =>{
    
            }
        )
    }else{
        return;
    }
    
  })