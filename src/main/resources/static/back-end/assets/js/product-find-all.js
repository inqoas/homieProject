var categoryMapping = {
    1: "清潔劑",
    2: "洗衣精",
    3: "打掃用品",
    4: "洗碗精",
};

function getCategoryText(categoryCode) {
    return categoryMapping[categoryCode] || "未知種類";
}


function init() {
    $.ajax({
        url: '../product/find-all',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#products-table');

            // 清空原本的內容
            tableBody.empty();

            response.forEach(function(product) {

                var categoryText = getCategoryText(product.product_category);

                var row = `
                    <tr>
                        <td>
                            <div class="table-image">
                                <img src="http://localhost:8080/homieProject/product/ProductFindImgController?product_id=${product.product_id}" class="img-fluid" alt="">
                            </div>
                        </td>
                        <td>${product.product_name}</td>
                        <td>${categoryText}</td>
                        <td>${product.product_stock}</td>
                        <td class="td-price">$${product.product_price}</td>
                        <td>
                            <ul>
                                <li>
                                    <a href="product-detail.html?product_id=${product.product_id}">
                                        <i class="ri-eye-line"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" class="delete-product" data-product-id="${product.product_id}" data-bs-toggle="modal" data-bs-target="#deleteModal">
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

    $(document).on('click', '.delete-product', function() {
        var productId = $(this).data('product-id');
        console.log(productId);
        $('#confirm-delete').data('product-id', productId);
    });
    
    $('#confirm-delete').click(function() {
        var productId = $(this).data('product-id');
        const deleteAPI = "../product/delete-by-id";
        $.ajax({
            url: `${deleteAPI}?product_id=${productId}`,
            type: 'GET',
            success: function(response) {
                if (response.success) {
                    $('#deleteModal').modal('hide');
                    init();
                } else {
                    alert('刪除失敗');
                }
            }
        });
    });
    
}

$(function() {
    init();
});
