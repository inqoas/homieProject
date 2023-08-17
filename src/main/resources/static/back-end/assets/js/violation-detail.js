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
const violationId = urlParams.get("violation_id");
const getViolationByIdAPI = '../violation/find-by-id'

$(function() {
    init();
    addBackButtonListener();
    addConfirmViolateButtonListener();
    addDeleteViolationButtonListener()
});

function init() {
    console.log(violationId);

    $.ajax({
        url: `${getViolationByIdAPI}?violation_id=${violationId}`,
        type: 'GET',
        dataType: "json",
        success: function(violation) {
            console.log(violation);

            $('#idInput').text(violation.violationId);
            $('#timeInput').text(violation.violationTime);
            $('#userIdReportInput').text(violation.userIdReport);
            $('#userIdReportedInput').text(violation.userIdReported);
            $('#directionInput').text(getDirectionText(violation.reportDirection));
            $('#categoryInput').text(getCategoryText(violation.violationCategory));
            $('#contentInput').val(violation.violationContent);
            
            const imagesDiv = document.getElementById('evidenceImages');

            for (let i = 1; i <= 5; i++) {
                const picKey = `violationPic${i}`;
                const url = violation[picKey];
                if (url) {
                    const img = document.createElement('img');
                    img.src = `data:image/jpeg;base64,${url}`;
                    img.width = 100;
                    img.style.marginRight = '10px';
                    img.style.cursor = 'pointer';
                    img.alt = '違規證據圖片 ' + i;
                    img.addEventListener('click', function() {
                        document.getElementById('modalImage').src = `data:image/jpeg;base64,${url}`;
                        new bootstrap.Modal(document.getElementById('imageModal')).show();
                    });
                    imagesDiv.appendChild(img);
                }
            }
        }
    });
}

function addBackButtonListener() {
    const backButton = document.getElementById("backButton");
    backButton.addEventListener("click", function () {
        history.back();
    });
}

function addConfirmViolateButtonListener(){
    const updateStatusAPI = `../violation/update-status`
    const confirmViolateButton = document.getElementById("confirmViolateButton");
    confirmViolateButton.addEventListener("click", function(){
        $.ajax({
            url: `${updateStatusAPI}?violation_id=${violationId}`,
            type: 'PUT',
            success: function(response) {
                alert(response);
                history.back();
            },
            error: function(response) {
                alert(response.responseText);
            }
        });
    });
}

function addDeleteViolationButtonListener() {
    const deleteViolationAPI = `../violation/delete`;
    const deleteViolationButton = document.getElementById("negativeViolateButton");
    deleteViolationButton.addEventListener("click", function() {
        if (confirm("確定要刪除這筆違規資料嗎？")) {
            $.ajax({
                url: `${deleteViolationAPI}?violation_id=${violationId}`,
                type: 'DELETE',
                success: function(response) {
                    alert(response);
                    history.back();
                },
                error: function(response) {
                    alert(response.responseText);
                }
            });
        }
    });
}


