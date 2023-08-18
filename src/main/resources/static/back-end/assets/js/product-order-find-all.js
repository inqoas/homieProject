const statusMapping = {
    0: "等待交易",
    1: "交易完成",
    2: "申請退貨",
    3: "退貨完成"
};

function getStatusText(statusCode) {
    return statusMapping[statusCode] || "未知狀態";
}

function filterOrdersByStatus(statusCode) {

    $.ajax({
        url: '../orderproduct/find-all',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#product-order-table');
            tableBody.empty();

            response.forEach(function(orderproduct) {
                if(statusCode ==="all" || orderproduct.product_status.toString() === statusCode){
                    var statusText = getStatusText(orderproduct.product_status)

                    var row = `
                        <tr>
                            <td>${orderproduct.order_product_id}</td>
                            <td>${orderproduct.user_id}</td>
                            <td>${orderproduct.product_placement_time}</td>
                            <td>${statusText}</td>
                            <td>${orderproduct.tracking_number}</td>
                            <td class="td-price">$${orderproduct.product_total}</td>
                            <td>
                                <ul>
                                    <li>
                                        <a href="product-order-detail.html?order_product_id=${orderproduct.order_product_id}">
                                            <i class="ri-eye-line"></i>
                                        </a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    `;
                    tableBody.append(row);
                }
            });
        }
    });
}

$(function() {
    filterOrdersByStatus('all');
});
