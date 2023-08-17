document.addEventListener("DOMContentLoaded", function () {
    try {
        const urlParams = new URLSearchParams(window.location.search);
        const orderServiceId = urlParams.get("order_service_id");
        
        fetchOrderService(orderServiceId);
        addBackButtonListener();
    } catch (error) {
        console.error("錯誤：", error);
    }
});

function fetchOrderService(orderServiceId) {
    const getOrderServiceAPI = "../orderservices/withServiceName";
    $.ajax({
        url: `${getOrderServiceAPI}?orderServiceId=${orderServiceId}`,
        type: 'GET',
        dataType: "json",
        success: function (orderServiceData) {
            console.log(orderServiceData);
            renderOrderService(orderServiceData);
        },
        error: function (error) {
            console.error("orderProductData錯誤:", error);
        }
    });
}

const statusMapping = {
    0: "等待服務",
    1: "服務完成",
    2: "申請取消",
    3: "取消完成"
};

function getStatusText(statusCode) {
    return statusMapping[statusCode] || "未知狀態";
}

function renderOrderService(orderServiceData) {
    var sec1 = $('#ajax1');
    sec1.empty();

    var orderServiceInfo = orderServiceData[0].orderService;
    var ServiceName = orderServiceData[0].serviceName;

        var data1 = `
            <div>
                <h5>訂單編號 :${orderServiceInfo.orderServiceId}</h5>
            </div>
            <div class="card-order-section">
                <ul>
                    <li>訂單成立時間：${orderServiceInfo.orderPlacementTime}</li>
                    <li>訂單狀態： ${getStatusText(orderServiceInfo.orderStatus)}</li>
                </ul>
            </div>
        `;
    sec1.append(data1);

    var sec2 = $('#ajax2');
    sec2.empty();

        var data2 = `
            <tr class="table-order" >
                <td>
                    <a href="javascript:void(0)">
                        <img src="http://localhost:8080/homieProject/service/Select_ImgController?service_id=${orderServiceInfo.orderServiceId}"
                            class="img-fluid blur-up lazyload" alt="">
                    </a>
                </td>
                <td>
                    <p style="margin: 0;">服務名稱：</p>
                    <h5>${ServiceName}</h5>
                </td>
                <td>
                    <p style="margin: 0;">提供服務人ID：</p>
                    <h5>${orderServiceInfo.userIdSeller}</h5>
                </td>
                <td>
                    <p style="margin: 0;">購買人ID：</p>
                    <h5>${orderServiceInfo.userIdBuyer}</h5>
                </td>
            </tr>
        `;
    sec2.append(data2);

    var sec3 = $('#ajax3');
    sec3.empty();

                        var data3 = `
                            <tr class="table-order">
                                <td colspan="3">
                                    <h4>購買數量 :</h4>
                                </td>
                                <td>
                                    <h4>X${orderServiceInfo.orderQuantity}</h4>
                                </td>
                            </tr>         
                            <tr class="table-order">
                                <td colspan="3">
                                    <h4>單價 :</h4>
                                </td>
                                <td>
                                    <h4>$${orderServiceInfo.orderUnitPrice}</h4>
                                </td>
                            </tr>
                            <tr class="table-order">
                                <td colspan="3">
                                    <h4 class="theme-color fw-bold">總金額 :</h4>
                                </td>
                                <td>
                                    <h4 class="theme-color fw-bold">$${orderServiceInfo.orderTotal}</h4>
                                </td>
                            </tr>
                        `;
    sec3.append(data3);

    var sec4 = $('#ajax4');
    sec4.empty();

    var data4 = `
        <h4>時間總覽</h4>
        <ul class="order-details">
            <li>預約服務日期: <br>${orderServiceInfo.orderServiceDate}</li>
            <li>服務完成日期: <br>${orderServiceInfo.orderServiceFinishDate}</li>
            <li>訂單完成日期: <br>${orderServiceInfo.orderServiceFinishTime}</li>
        </ul>

        <h4>評價</h4>
        <ul class="order-details">
            <li>${orderServiceInfo.reviewContent}</li>
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