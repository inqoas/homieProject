const user_account = document.querySelector("#user_account");
const user_password = document.querySelector("#user_password");
const user_name = document.querySelector("#user_name");
const user_ic = document.querySelector("#user_ic");
const user_phone = document.querySelector("#user_phone");
const user_birth = document.querySelector("#user_birth");
const user_gender = document.querySelector("#user_gender");
const user_city = document.querySelector("#user_city");
const user_address = document.querySelector("#user_address");


document.querySelector("#sign-up-submit").addEventListener("click", function () {

  fetch("../signup", {
    method:"POST",
    headers:{
      "Content-Type" : "application/json"
    },
    body: JSON.stringify({
      user_account:user_account.value,
      user_password: user_password.value,
      user_name: user_name.value,
      user_ic: user_ic.value,
      user_phone: user_phone.value,
      user_birth: user_birth.value,
      user_gender: user_gender.value,
      user_address: user_city.value + user_address.value,

  })
  }).then(
    function(resp){
      return resp.json();
    }
  ).then(
    function(body){
      alert(body.message);
      if(body.success === true){
        location = "../front-end/login.html";
    }

    },
    function(error){

    }
  )

});
