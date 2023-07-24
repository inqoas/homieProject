function init() {
    $.ajax({
        url: '../product/findall',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#products-table');

            // 清空原本的內容
            tableBody.empty();

            response.forEach(function(product) {
                var row = `
                    <tr>
                        <td>
                            <div class="table-image">
                                <img src="assets/images/product/${product.product_picture}.png" class="img-fluid" alt="">
                            </div>
                        </td>
                        <td>${product.product_name}</td>
                        <td>${product.product_category}</td>
                        <td>${product.product_stock}</td>
                        <td class="td-price">$${product.product_price}</td>
                        <td class="status-danger">
                            <span>Pending</span>
                        </td>
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
