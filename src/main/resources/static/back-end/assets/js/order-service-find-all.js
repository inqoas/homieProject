const statusMapping = {
    0: "等待交易",
    1: "交易完成",
    2: "申請退款",
    3: "退款完成"
};

function getStatusText(statusCode) {
    return statusMapping[statusCode] || "未知狀態";
}

function init() {

    $.ajax({
        url: '../orderservice',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#service-order-table');

            console.log(response);

            // 清空原本的內容
            tableBody.empty();

            response.forEach(function(orderservice) {

                var statusText = getStatusText(orderservice.orderStatus)

                var row = `
                    <tr>
                        <td>${orderservice.orderServiceId}</td>
                        <td>${orderservice.userIdBuyer}</td>
                        <td>${orderservice.userIdSeller}</td>
                        <td>${orderservice.orderPlacementTime}</td>
                        <td>${statusText}</td>
                        <td class="td-price">$${orderservice.orderTotal}</td>
                        <td>
                            <ul>
                                <li>
                                    <a href="">
                                        <i class="ri-eye-line"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="ri-pencil-line"></i>
                                    </a>
                                </li>
                            </ul>
                        </td>
                    </tr>
                `;
                tableBody.append(row);
            });
        }
    });
}

$(function() {
    init();
});