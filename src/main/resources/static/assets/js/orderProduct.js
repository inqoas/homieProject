const user_jwt = localStorage.getItem('user_jwt');
// 獲取完整的網址
const url = window.location.href;

// 解析網址，取得參數部分
const params = new URLSearchParams(new URL(url).search);

// 獲取 orderProductId 參數的值
const orderProductId = params.get('orderProductId');

$(document).ready(function(){
    fetch(`../userOrderProductDetailController/getOrderProductDetail?orderProductId=${orderProductId}`, {
        method: "GET",
        headers:{
            "Authorization": "Bearer " + user_jwt
        }
    }).then(resp => {
        return resp.json();
    }).then(
        body =>{
            const userinfo = body['userinfo'];
            const orderProduct = body['orderProduct'];
            const orderProductDetails = body['orderProductDetails'];
            let orderProductStatus = "";
            let orderProductDetailsHTML = "";
            if(orderProduct.product_status == 0){
                orderProductStatus = "未結帳";
            }else if(orderProduct.product_status == 1){
                orderProductStatus = "等待完成";
            }else if(orderProduct.product_status == 2){
                orderProductStatus = "交易完成";
            }else if(orderProduct.product_status == 3){
                orderProductStatus = "退貨";
            }
            $(".id").html(orderProduct.order_product_id);
            $(".orderprice").html(orderProduct.product_total);
            $(".status").html(orderProductStatus);
            $(".buyerName").html(userinfo.user_name);
            $(".buyerMail").html(userinfo.user_account);
            $(".buyerPhone").html(userinfo.user_phone);
            $(".buyerAddress").html(userinfo.user_address);
            orderProductDetails.forEach(collection => {
                let detailName = collection.productName;
                let detailCount = collection.productQuantity;
                let detailPrice = collection.productPrice;
                
                orderProductDetailsHTML += `
                                <tr>
                                    <td class="id" style="background-color:white;">${orderProduct.order_product_id}</td>
                                    <td class="name" style="width: 40%; background-color: white; word-break: break-all;">${detailName}</td>
                                    <td class="count" style="background-color:white;">${detailCount}</td>
                                    <td class="price" style="background-color:white;">${detailPrice}</td>
                                    <td class="allprice" style="background-color:white;">${detailCount * detailPrice}</td>
                                </tr>
                    `
            });
            $(".allOrderDetail").html(orderProductDetailsHTML);
            $(".GC").html(orderProduct.product_deduct_gc);
            $(".fullprice").html(orderProduct.product_total);
        },
        error =>{
    
        }
    )
})
