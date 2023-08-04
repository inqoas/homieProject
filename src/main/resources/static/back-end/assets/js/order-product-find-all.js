function init() {
    $.ajax({
        url: '../orderproduct/find-all',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#product-order-table');

            // 清空原本的內容
            tableBody.empty();

            response.forEach(function(orderproduct) {
                var row = `
                    <tr>
                        <td>${orderproduct.order_product_id}</td>
                        <td>${orderproduct.user_id}</td>
                        <td>${orderproduct.product_placement_time}</td>
                        <td>${orderproduct.product_status}</td>
                        <td>${orderproduct.tracking_number}</td>
                        <td class="td-price">$${orderproduct.product_total}</td>
                        <td>
                            <ul>
                                <li>
                                    <a href="order-detail.html">
                                        <i class="ri-eye-line"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="ri-pencil-line"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" data-bs-toggle="modal" data-bs-target="#exampleModalToggle">
                                        <i class="ri-delete-bin-line"></i>
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
