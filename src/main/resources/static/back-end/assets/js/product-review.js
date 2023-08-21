function init(){
    $.ajax({
        url: '../product/find-all',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#reviews-table');

            // 清空原本的內容
            tableBody.empty();

            response.forEach(function(product) {

                var totalStars = product.product_review_stars;
                var totalReviews = product.product_review_count;

                var starsHtml = createRatingStars(totalStars, totalReviews);
                

                var row = `
                    <tr>
                        <td>
                            <div class="table-image">
                                <img src="http://localhost:8080/homieProject/product/ProductFindImgController?product_id=${product.product_id}" class="img-fluid" alt="">
                            </div>
                        </td>
                        <td>${product.product_id}</td>
                        <td>${product.product_name}</td>
                        <td>${starsHtml}</td>
                        <td>${product.product_review_count}</td>
                        </td>
                    </tr>
                `;
                tableBody.append(row);
            });
        }
    });

    $('#searchInput').on('input', function() {
        var searchTerm = $(this).val().toLowerCase();

        $('#reviews-table tr').each(function() {
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
};

function createRatingStars(totalStars, totalReviews) {
    var averageRating = totalStars / totalReviews || 0;
    var fullStars = Math.floor(averageRating);
    var hasHalfStar = averageRating % 1 >= 0.5;
    var html = '<ul class="rating">';
  
    // 加上全星
    for (var i = 0; i < fullStars; i++) {
      html += '<li><i class="fas fa-star theme-color"></i></li>';
    }
  
    // 如果有半星
    if (hasHalfStar) {
      html += '<li><i class="fas fa-star-half theme-color"></i></li>';
    }
  
    // 剩下的空星
    for (var i = fullStars + (hasHalfStar ? 1 : 0); i < 5; i++) {
      html += '<li><i class="fas fa-star"></i></li>';
    }
  
    html += '</ul>';
  
    return html;
}

  

$(function() {
    init();
});