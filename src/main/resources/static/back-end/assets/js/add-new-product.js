document.addEventListener("DOMContentLoaded", function () {
    try {
        
        CKEDITOR.replace('editor');
        const confirmAddButton = document.getElementById("confirmAddButton");
        const confirmButton = document.getElementById("confirmButton")
        const errorMessageDiv = document.getElementById("errorMessage");

        confirmButton.addEventListener("click", function(){
            errorMessageDiv.innerHTML = '';
        });

        confirmAddButton.addEventListener("click", function () {
            
            var product_name = document.getElementById("product_name").value;
            var product_category = document.getElementById("product_category").value;
            var product_introduction = document.getElementById("product_introduction").value;
            var product_price = document.getElementById("product_price").value;
            var product_stock = document.getElementById("product_stock").value;

            var validationError = validateForm(product_name, product_introduction, product_price, product_stock);
            if (validationError) {
                errorMessageDiv.innerHTML = validationError; 
                return; 
            }

            var fileInput = document.getElementById("product_picture");
            var product_picture = fileInput.files[0];

            var formData = new FormData();
            formData.append("product_name", product_name);
            formData.append("product_category", product_category);
            formData.append("product_introduction", product_introduction);
            formData.append("product_price", product_price);
            formData.append("product_stock", product_stock);
            formData.append("product_picture", product_picture);

            $.ajax({
                url: "../product/add-new",
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    console.log("新增商品成功：", data);
                    alert("新增商品成功！");
                    window.location.href = 'products.html';
                },
                error: function (error) {
                    console.error("新增商品失敗：", error);
                }
            });
        });

        const backButton = document.getElementById("backButton");
        backButton.addEventListener("click", function () {
            history.back();
        });
    } catch (error) {
        console.error("無法取得新增商品：", error);
    }
});
function validateForm(product_name, product_introduction, product_price, product_stock) {
    var errors = [];
 
    if (product_name.length < 3) {
        errors.push('商品名稱必須至少三個字！');
    }

    if (parseFloat(product_price) < 0) {
        errors.push('單價不能為負數！');
    }

    if (parseInt(product_stock) < 1) {
        errors.push('庫存數量至少為1！');
    }

    if (product_introduction.length < 10) {
        errors.push('商品介紹必須至少10字！');
    }

    if (errors.length > 0) {
        return errors.join('<br/>');
    }

    return null;
}








