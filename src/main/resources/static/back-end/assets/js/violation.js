var categoryMapping = {
    0: "惡意下單",
    1: "不當行為",
    2: "虛假宣傳",
    3: "不當服務",
};

function getCategoryText(categoryCode) {
    return categoryMapping[categoryCode] || "未知種類";
}

var directionMapping = {
    0: "買家檢舉賣家",
    1: "賣家檢舉買家",
};

function getDirectionText(directionCode) {
    return directionMapping[directionCode] || "未知種類";
}


const urlParams = new URLSearchParams(window.location.search);
const violationStatus = urlParams.get("violation_status");
const getViolationAPI = '../violation/find-by-status'

function init() {
    $.ajax({
        url: `${getViolationAPI}?violation_status=${violationStatus}`,
        type: 'GET',
        dataType: "json",
        success: function(response) {
            var tableBody = $('#violation-users-table');

            // 清空原本的內容
            tableBody.empty();

            response.forEach(function(violation) {

                var categoryText = getCategoryText(violation.violationCategory);
                var directionText = getDirectionText(violation.reportDirection);

                var row = `
                    <tr>
                        <td>${violation.userIdReport}</td>
                        <td>${violation.userIdReported}</td>
                        <td>${directionText}</td>
                        <td>${categoryText}</td>
                        <td>
                            <ul>
                                <li>
                                    <a href="violation-detail.html?violation_id=${violation.violationId}">
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
    
}
$(function() {
    init();
});