



const registerClick = () => {
    console.log('adding User');
    let email = document.querySelector("#email").value;
    let username = document.querySelector("#usernameRegister").value;
    let password = document.querySelector("#passwordRegister").value;
    let securityRoles = ["USER"];
    let isActive = true;
    let newUser = JSON.stringify({username, password, email, securityRoles, isActive});
    console.log(newUser);
    const request = new XMLHttpRequest();
    request.open("POST", "http://localhost:80/registration/createUser");
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(newUser);
    request.onload = () =>{
        alert(request.responseText)

    }
}