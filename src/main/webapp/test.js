// const logoutBtn = document.getElementById("logout");
//
//
// const method_Logout = () => {
//     console.log("Event Listener Hit");
//     const request = new XMLHttpRequest();
//     request.open("GET", "http://localhost:80/chadroom/logout");
//     request.send();
//     request.onload = () => {
//         alert(request.responseText)
//     }
// };

function userLogin() {
    //console.log("Login Event Hit");
    //console.log("Username = " + document.getElementById("usernameLogin").valueOf());
    let username = document.getElementById("usernameLogin").valueOf();

    const request = new XMLHttpRequest();
    request.open("GET", "http://localhost:80/anyone/login");
    request.send();
    request.onload = () => {
        //alert(request.responseText)
        location.href = request.responseText;
    }
}

function userLogout() {
    //console.log("Logout Event Hit");
    const request = new XMLHttpRequest();
    request.open("GET", "http://localhost:80/anyone/logout");
    request.send();
    request.onload = () => {
        //alert(request.responseText)
        //location.href = request.responseText;
    }
}