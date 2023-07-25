// var host = window.location.host;
// var pathname = window.location.pathname;
// var apiUrl = 'http://' + host + pathname + 'homie/all-users'

// console.log(host);
// console.log(pathname);

function init(){

    $.ajax({
        url: '../userinfo/findall',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#all-users-table');
        
            // 清空原本的內容
            tableBody.empty();
        
            // 插入待審核賣家的資料
            response.forEach(function(userinfo) {
                var row = `
                    <tr>
                        <td>
                            <div class="table-image">
                                <img src=" http://localhost:8080/homieProject/product/ProductFindImgController?product_id= ${userinfo.user_id}" class="img-fluid" alt="">
                            </div>
                        </td>
                        <td>
                            <div class="user-name">
                                <span>${userinfo.user_name}</span>
                                <span>${userinfo.user_address}</span>
                            </div>
                        </td>
                        <td>${userinfo.user_phone}</td>
                        <td>${userinfo.user_account}</td>
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

$(function(){
    init();
});

