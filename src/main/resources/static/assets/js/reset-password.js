const user_jwt = localStorage.getItem('user_jwt');

document.querySelector("#submitpassword").addEventListener("click", function(){
    const oldPassword = document.querySelector("#oldPassword").value;
    const newPassword = document.querySelector("#newPassword").value;
    const newPasswordCheck = document.querySelector("#newPasswordCheck").value;
    console.log(oldPassword)
    console.log(newPassword)
    console.log(newPasswordCheck)
    if(newPassword != newPasswordCheck){
        alert("新密碼與確認密碼不一致，請重新確認。");
        return;
    }
    fetch("../changeUserPassword",{
        method:"POST",
        headers:{
            "Content-Type": "application/json",
            "Authorization": "Bearer " + user_jwt
        },
        body: JSON.stringify({
            user_password: newPassword,
            message: oldPassword
        })
    }).then(
        function(resp){
            return resp.json();
        }
    ).then(
        function(body){
            alert(body.message);
            if(body.success == true){
                localStorage.removeItem("user_jwt");
                location = "../front-end/login.html";
            }
        },
        function(error){

        }
    )
})