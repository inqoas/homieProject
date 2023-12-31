var statusMapping = {
    0: "正常",
    1: "停權",
    2: "未審核",
    3: "註銷",
};

function getStatusText(statusCode) {
    return statusMapping[statusCode] || "未知狀態";
}

function init(){

    $.ajax({
        url: '../userinfo/find-all',
        type: 'GET',
        dataType: "json",
        success: function(response) {

            var tableBody = $('#all-users-table');
        
            tableBody.empty();
        
            response.forEach(function(userinfo) {

                var statusText = getStatusText(userinfo.user_status);

                var row = `
                    <tr>
                        <td>
                            <div class="table-image">
                                <img src=" http://localhost:8080/homieProject//userinfo/user-info-find-img?user_id=${userinfo.user_id}" class="img-fluid" alt="">
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
                        <td>${statusText}</td>
                        <td>
                            <ul>
                                <li>
                                    <a href="user-detail.html?user_id=${userinfo.user_id}">
                                        <i class="ri-eye-line"></i>
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
                $(this).show(); 
            } else {
                $(this).hide(); 
            }
        });
    });
}

$(function(){
    init();
});

