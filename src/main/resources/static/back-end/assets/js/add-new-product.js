var categoryMapping = {
    "清潔劑": 1,
    "洗衣精": 2,
    "打掃用品": 3,
    "洗碗精": 4,
};

function getCategoryCode(categoryText) {
    return categoryMapping[categoryText] || "未知種類";
}

document.addEventListener("DOMContentLoaded", function () {
    try {
        
        CKEDITOR.replace('editor');
        const confirmAddButton = document.getElementById("confirmAddButton");
        confirmAddButton.addEventListener("click", function () {
            
            var product_name = document.getElementById("product_name").value;
            var product_category = getCategoryCode(document.getElementById("product_category").value);
            var product_introduction = document.getElementById("product_introduction").value;
            var product_price = document.getElementById("product_price").value;
            var product_stock = document.getElementById("product_stock").value;

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
                    window.location.href = 'products.html'
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
