document.addEventListener("DOMContentLoaded", function () {
    try {
        const urlParams = new URLSearchParams(window.location.search);
        const orderProductId = urlParams.get("order_product_id");
        fetchOrderProduct(orderProductId);
        addBackButtonListener();
    } catch (error) {
        console.error("錯誤：", error);
    }
});

function fetchOrderProduct(orderProductId) {
    const getOrderProductAPI = "../orderproduct/find-by-order-product-id";
    $.ajax({
        url: `${getOrderProductAPI}?order_product_id=${orderProductId}`,
        type: 'GET',
        dataType: "json",
        success: function (orderProductData) {
            renderOrderProduct(orderProductData);
            fetchOrderProductDetail(orderProductId, orderProductData.user_id, orderProductData);
        },
        error: function (error) {
            console.error("orderProductData錯誤:", error);
        }
    });
}

function fetchOrderProductDetail(orderProductId, userId, orderProductData) {
    const getOrderProductDetailAPI = "../orderproductdetail/find-order-detail-by-order-product-id";
    $.ajax({
        url: `${getOrderProductDetailAPI}?order_product_id=${orderProductId}`,
        type: 'GET',
        dataType: "json",
        success: function (orderProductDetailData) {
            renderOrderProductDetail(orderProductDetailData, userId, orderProductData);
        },
        error: function (error) {
            console.error("orderProductDetailData錯誤:", error);
        }
    });
}

function renderOrderProduct(orderProductData) {
    var sec1 = $('#ajax1');
    sec1.empty();

        var data1 = `
            <div>
                <h5>訂單編號 :${orderProductData.order_product_id}</h5>
            </div>
            <div class="card-order-section">
                <ul>
                    <li>訂單成立時間：${orderProductData.product_placement_time}</li>
                    <li>購買人編號： ${orderProductData.user_id}</li>
                </ul>
            </div>
        `;
    sec1.append(data1);
}

function renderOrderProductDetail(orderProductDetailData, userId, orderProductData) {
    var sec2 = $('#ajax2');
    sec2.empty();

    let totalAmount = 0;
        
    orderProductDetailData.forEach(function(orderproductdetail) {
        var data2 = `
            <tr class="table-order" >
                <td>
                    <a href="javascript:void(0)">
                        <img src="http://localhost:8080/homieProject/product/ProductFindImgController?product_id=${orderproductdetail.productId}"
                            class="img-fluid blur-up lazyload" alt="">
                    </a>
                </td>
                <td>
                    <p>產品名稱：</p>
                    <h5>${orderproductdetail.productName}</h5>
                </td>
                <td>
                    <p>數量：</p>
                    <h5>${orderproductdetail.productQuantity}</h5>
                </td>
                <td>
                    <p>單價：</p>
                    <h5>$${orderproductdetail.productPrice}</h5>
                </td>
            </tr>
        `;
    sec2.append(data2);
    totalAmount += orderproductdetail.productQuantity * orderproductdetail.productPrice;
    });
    var sec3 = $('#ajax3');
    sec3.empty();

                        var data3 = `                       
                            <tr class="table-order">
                                <td colspan="3">
                                    <h4 class="theme-color fw-bold">總金額 :</h4>
                                </td>
                                <td>
                                    <h4 class="theme-color fw-bold">$${totalAmount}</h4>
                                </td>
                            </tr>
                        `;
    sec3.append(data3);
    fetchUserAddress(userId, totalAmount, orderProductData);
}

function fetchUserAddress(userId, totalAmount, orderProductData) {
    const getUserAddressAPI = "../userinfo/find-address";
    $.ajax({
        url: `${getUserAddressAPI}?user_id=${userId}`,
        type: 'GET',
        dataType: "json",
        success: function (userAddress) {
            renderUserAddress(userAddress, totalAmount, orderProductData);
        },
        error: function (error) {
            console.error("userAddress錯誤:", error);
        }
    });
}

function renderUserAddress(userAddress, totalAmount, orderProductData) {
    var sec4 = $('#ajax4');
    sec4.empty();

    var data4 = `
        <h4>總覽</h4>
        <ul class="order-details">
            <li>下單日期： <br>${orderProductData.product_placement_time}</li>
            <li>訂單金額： <br>$${totalAmount}</li>
        </ul>

        <h4>寄送地址</h4>
        <ul class="order-details">
            <li>${userAddress.user_address}</li>
        </ul>

        <div class="payment-mode">
            <h4>付款方式</h4>
            <p>綠界支付</p>
        </div>          
    `;
    sec4.append(data4);
}

function addBackButtonListener() {
    const backButton = document.getElementById("backButton");
    backButton.addEventListener("click", function () {
        history.back();
    });
}

