function init(){
    $.ajax({
        url: '',
        method: 'GET',
        success: function(response) {
            var tableBody = $('#pending-sellers-table');
        
            // 清空原本的內容
            tableBody.empty();
        
            // 插入待審核賣家的資料
            response.forEach(function(seller) {
                var row = `
                    <tr>
                        <td>
                            <div class="table-image">
                                <img src="path/to/image/${seller.user_id}.jpg" class="img-fluid" alt="">
                            </div>
                        </td>
                        <td>
                            <div class="user-name">
                                <span>${seller.user_name}</span>
                                <span>${seller.user_address}</span>
                            </div>
                        </td>
                        <td>${seller.user_phone}</td>
                        <td>${seller.user_account}</td>
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
