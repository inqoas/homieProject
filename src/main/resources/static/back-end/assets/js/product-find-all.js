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
                                <img src=" http://localhost:8080/homieProject/product/ProductFindImgController?product_id=${product.product_id}" class="img-fluid" alt="">
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

    $('#searchInput').on('input', function() {
        var searchTerm = $(this).val().toLowerCase();

        $('#products-table tr').each(function() {
            var productName = $(this).find('td:nth-child(2)').text().toLowerCase();
            var productCategory = $(this).find('td:nth-child(3)').text().toLowerCase();
            var productStock = $(this).find('td:nth-child(4)').text().toLowerCase();
            var productPrice = $(this).find('td:nth-child(5)').text().toLowerCase();

            if (productName.includes(searchTerm) || productCategory.includes(searchTerm) ||
                productStock.includes(searchTerm) || productPrice.includes(searchTerm)) {
                $(this).removeClass('hidden');
            } else {
                $(this).addClass('hidden');
            }
        });
    });
}

$(function() {
    init();
});
