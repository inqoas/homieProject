var hasChanges = false;

document.addEventListener("DOMContentLoaded", function () {
    try {
        const urlParams = new URLSearchParams(window.location.search);
        const productId = urlParams.get("product_id");
        const getProductAPI = "../product/find-by-id";


        fetchProductDetails(getProductAPI, productId);
        handleChanges();
    } catch (error) {
        console.error("無法取得商品明細：", error);
    }
});

function fetchProductDetails(api, productId) {
    $.ajax({
        url: `${api}?product_id=${productId}`,
        type: 'GET',
        dataType: "json",
        success: function(product) {
            fillProductDetails(product);
            handleImageUpdate(product);
            handleChanges();
            handleBackButton(hasChanges);
        },
        error: function (error) {
            console.error("fetchProductDetails：", error);
        }
    });
}

var initialValues = {};

function fillProductDetails(product) {
    initialValues = {
        name: product.product_name,
        price: product.product_price,
        stock: product.product_stock,
        category: product.product_category,
        intro: product.product_introduction
    };
    const getProductImageAPI = `http://localhost:8080/homieProject/product/ProductFindImgController?product_id=${product.product_id}`;
    const productImage = document.getElementById("productImage");
    productImage.src = getProductImageAPI;
    const productContainer = document.getElementById("productContainer");
    productContainer.querySelector('#nameInput').value = product.product_name;
    productContainer.querySelector('#idInput').textContent = product.product_id;
    productContainer.querySelector('#priceInput').value = product.product_price;
    productContainer.querySelector('#stockInput').value = product.product_stock;
    productContainer.querySelector('#shippedInput').textContent = product.product_shipped;
    productContainer.querySelector('#categoryInput').value = product.product_category;
    productContainer.querySelector('#introInput').value = product.product_introduction;
    productContainer.querySelector('#starsInput').textContent = product.product_review_stars;
    productContainer.querySelector('#reviewsInput').textContent = product.product_review_count;

    return initialValues;
}

function handleImageUpdate(product) {
    const getProductImageAPI = `http://localhost:8080/homieProject/product/ProductFindImgController?product_id=${product.product_id}`;
    const productImage = document.getElementById("productImage");

    $('#imageUpdateButton').on('click', function() {
        var formDataPic = new FormData();
        var fileInput = document.getElementById("imageUpload");
        var product_picture = fileInput.files[0];
        formDataPic.append('product_picture', product_picture);
        formDataPic.append('product_id', $('#idInput').text());
    
        $.ajax({
            url: '../product/update-picture', 
            type: 'POST',
            data: formDataPic,
            contentType: false,
            processData: false,
            success: function(response) {
                if (response.success) {
                    alert('圖片更新成功');
                    productImage.src = getProductImageAPI + '&timestamp=' + new Date().getTime();
                } else {
                    alert('圖片更新失敗');
                }
            }
        });
    });
}

function handleChanges() {

    $('input, select, textarea').on('input change', function() {
        hasChanges =
            initialValues.name !== $('#nameInput').val() ||
            parseFloat(initialValues.price) !== parseFloat($('#priceInput').val()) ||
            parseInt(initialValues.stock) !== parseInt($('#stockInput').val()) ||
            parseInt(initialValues.category) !== parseInt($('#categoryInput').val()) ||
            initialValues.intro !== $('#introInput').val();

    });
}

function handleBackButton() {
    const backButton = document.getElementById("backButton");
    backButton.addEventListener("click", function () {
        if (hasChanges) {
            
            var isConfirmed = confirm('您的內容有變動，是否保存更改？');
            if (isConfirmed) {
                var productData = {
                    product_name: $('#nameInput').val(),
                    product_id: $('#idInput').text(),
                    product_price: $('#priceInput').val(),
                    product_stock: $('#stockInput').val(),
                    product_shipped: $('#shippedInput').text(),
                    product_category: $('#categoryInput').val(),
                    product_introduction: $('#introInput').val(),
                    product_review_stars: $('#starsInput').text(),
                    product_review_count: $('#reviewsInput').text()
                };

                if (!validateFormData(productData)) {
                    return; 
                }

                $.ajax({
                    url: '../product/update', 
                    type: 'POST',
                    data: JSON.stringify(productData),
                    contentType: 'application/json',
                    success: function(response) {
                        if (response.success) {
                            alert('更新成功');
                        } else {
                            alert('更新失敗');
                        }
                    }
                });
            }
        }
        window.location.href = 'products.html';
    });
}

function validateFormData(productData) {
    // 商品名稱：至少三個字
    if (productData.product_name.length < 3) {
        alert('商品名稱必須至少三個字！');
        return false;
    }

    // 單價：不能為負數
    if (parseFloat(productData.product_price) < 0) {
        alert('單價不能為負數！');
        return false;
    }

    // 庫存數量：至少為1
    if (parseInt(productData.product_stock) < 1) {
        alert('庫存數量至少為1！');
        return false;
    }

    // 商品介紹：至少10字
    if (productData.product_introduction.length < 10) {
        alert('商品介紹必須至少10字！');
        return false;
    }

    return true;
}
