const user_jwt = localStorage.getItem('user_jwt');

document.querySelector(".sellerSubmit").addEventListener("click",function(){
    const bankCode = document.querySelector(".sellerBCode").value || 0;
    const bankAccount = document.querySelector(".sellerBAccount").value || "";
    const bankName = document.querySelector(".sellerBName").value || "";
    const bankPcrcInput = document.querySelector(".sellerPcrc");
    if(bankPcrcInput.files.length < 1){
        alert("請上傳良民證圖片")
        return;
    }
    const formData = new FormData();
    formData.append('bankCode', bankCode);
    formData.append('bankAccount', bankAccount);
    formData.append('bankHolderName', bankName);
    formData.append('sellerPcrc', bankPcrcInput.files[0]);
    fetch("../sellerProfile/insertSeller", {
        method:"POST",
        headers:{"Authorization": "Bearer " + user_jwt},
        body: formData
    }).then(resp => {
        return resp.json();
    }).then(
        body => {
            if(body.message != null){
                alert(body.message);
            }
            if(body.success == false && body.message == "驗證失敗，請重新登入"){
                localStorage.removeItem("user_jwt");
                location = "../front-end/login.html";
            }
            if(body.success == true){
                location = "../front-end/user-dashboard.html";
            }
        },
        error =>{

        }
    )
})