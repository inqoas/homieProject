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
                                <img src="path/to/image/${userinfo.user_id}.jpg" class="img-fluid" alt="">
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

    $('#searchInput').on('input', function() {
        var searchTerm = $(this).val().toLowerCase();

        $('#all-users-table tr').each(function() {
            var userName = $(this).find('.user-name').text().toLowerCase();
            var userPhone = $(this).find('td:nth-child(3)').text().toLowerCase();
            var userEmail = $(this).find('td:nth-child(4)').text().toLowerCase();

            if (userName.includes(searchTerm) || userPhone.includes(searchTerm) || userEmail.includes(searchTerm)) {
                $(this).show(); // 符合搜尋條件，顯示該行
            } else {
                $(this).hide(); // 不符合搜尋條件，隱藏該行
            }
        });
    });
}

$(function(){
    init();
});

