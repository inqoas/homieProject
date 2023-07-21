const user_account =  document.querySelector("#user_account");
const user_password =  document.querySelector("#user_password");
const user_name =  document.querySelector("#user_name");
const user_ic =  document.querySelector("#user_ic");
const user_phone =  document.querySelector("#user_phone");
const user_birth =  document.querySelector("#user_birth");
const user_gender =  document.querySelector("#user_gender");
const user_city =  document.querySelector("#user_city");
const user_address =  document.querySelector("#user_address");

document.querySelector("#sign-up-check").addEventListener("click", function() {
    var errorMessages = [];
  
    if (!user_account.value) {
      errorMessages.push("請輸入帳號");
    }
  
    if (user_password.value.length < 8) {
      errorMessages.push("密碼需至少包含8個字元");
    }
  
    if (!user_password.value) {
      errorMessages.push("請輸入密碼");
    }
  
    if (!user_name.value) {
      errorMessages.push("請輸入姓名");
    }
  
    if (user_ic.value.length < 10) {
      errorMessages.push("身份證字號至少包含10個字元");
    }
  
    if (!user_ic.value) {
      errorMessages.push("請輸入身分證字號");
    }
  
    if (!user_phone.value) {
      errorMessages.push("請輸入電話號碼");
    }
  
    if (!user_birth.value) {
      errorMessages.push("請輸入生日");
    }
  
    if (!user_city.value) {
      errorMessages.push("請輸入縣市");
    }
  
    if (!user_address.value) {
      errorMessages.push("請輸入地址");
    }
  
    if (errorMessages.length > 0) {
      alert(errorMessages.join("\n"));
      return;
    }
  
    // 所有輸入驗證通過，執行註冊操作
  });
  
