$(function() {
    init();
});

function init() {
    $.ajax({
        url: '../announcement',
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#list-page-body');

            // 清空原本的內容
            tableBody.empty();

            response.forEach(function(announcement) {


                var row = `
                    <tr>
                        <td>${announcement.articleTitle}</td>
                        <td>${announcement.articleId}</td>
                        <td>${announcement.empId}</td>
                        <td>${announcement.uploadDate}</td>
                        <td>
                            <ul>
                                <li>
                                    <a href="javascript:void(0)">
                                        <i class="ri-pencil-line"></i>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0)" class="delete-article" data-article-id="${announcement.articleId}" data-bs-toggle="modal" data-bs-target="#deleteModal">
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

    $(document).on('click', '.delete-article', function() {
        var articleId = $(this).data('article-id');
        console.log(articleId);
        $('#confirm-delete').data('article-id', articleId);
    });

    $('#confirm-delete').click(function() {
        var articleId = $(this).data('article-id');
        console.log(articleId);
        const deleteAPI = `../announcement/${articleId}`;

        $.ajax({
            url: deleteAPI,
            type: 'DELETE',
            success: function() {
                $('#deleteModal').modal('hide');
                alert("刪除成功")
                init();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('刪除失敗: ' + textStatus, errorThrown);
                alert('刪除失敗');
            }
        });
    });
}