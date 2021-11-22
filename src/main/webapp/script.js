//const express = require('express'); had to do these to get them installed, there is not a simple npm install window -_-
//const {MongoClient, ObjectId} = require("mongodb");
// const http = require('http');
// const { Server } = require("socket.io");

summonChad = () => {
    //window.open("https://static.wikia.nocookie.net/be-like-bro/images/c/cf/Chad.jpg/revision/latest/top-crop/width/360/height/450?cb=20180403173350", '_blank');
    window.open("https://i.kym-cdn.com/photos/images/original/001/318/180/0eb.jpg", '_blank');
}

const email_pattern =/.+@.{2,}\..{2,}/i;
const password_pattern = /(?=.{8,})(?=.*\d{1,})(?=.*[A-Z]{1,})(?=.*[\/,.;:!@#$%^&*()\[\]\{\}_+=<>?]{1,})(?!.\s).*$/;
const username_pattern =/^[^_0-9]\w{4,32}$/;

const email = document.getElementById('email');
const usernameRegister = document.getElementById('usernameRegister');
const passwordRegister = document.getElementById('passwordRegister');
const passwordConfirm = document.getElementById('passwordConfirm');
const emailRegex = document.getElementById('emailRegex');
const usernameRegex = document.getElementById('usernameRegex');
const passwordRegex = document.getElementById('passwordRegex');
const confirmRegex = document.getElementById('confirmRegex');
let registerEmailError = true;
let registerUsernameError = true;
let registerPasswordError = true;
let passwordConfirmError = true;
let hasErrors = true;

emailChecker = () => {
    registerEmailError = true;
    if(!email_pattern.test(email.value)) {
        emailRegex.innerHTML = 'Please enter a valid Email <br/><br/>';
    } else {
        emailRegex.innerHTML = '';
        registerEmailError = false;
    }
}

usernameChecker = () => {
    registerUsernameError = true;
    if(!username_pattern.test(usernameRegister.value)) {
        usernameRegex.innerHTML = 'Valid names must be between 5 and 32 characters and only contain alphanumerics and underscores, as well as start with a letter <br/><br/>';
    } else {
        usernameRegex.innerHTML = '';
        registerUsernameError = false;
    }
    //TODO actually do this thing (check mongo's existing users and make sure the username is available.)
}

passwordChecker = () => {
    registerPasswordError = true;
    if(!password_pattern.test(passwordRegister.value)) {
        passwordRegex.innerHTML = 'Password must be at least 8 characters and contain at least one uppercase letter, one number, and one special character <br/><br/>';
    } else {
        passwordRegex.innerHTML = '';
        registerPasswordError = false;
    }
}

confirmChecker = () => {
    passwordConfirmError = true;
    if(passwordConfirm.value != passwordRegister.value) {
        confirmRegex.innerHTML = 'Passwords do not match <br/><br/>';
    } else {
        confirmRegex.innerHTML = '';
        passwordConfirmError = false;
    }
}

errorChecker = () => {
    if(!registerEmailError) {
        if(!registerUsernameError){
            if(!registerPasswordError) {
                if(!passwordConfirmError) {
                    hasErrors = false;
                }
            }
        }
    }
}

loginOnClick = () => {
    //TODO Verify input and start session if input is good and switch to the chatroom
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
}

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

const addUser = () => {
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
        //alert(request.responseText)
        if (request.responseText === "Username or Email already exists!"){
            usernameRegex.innerHTML = request.responseText;
        }else{
            location.href = 'http://localhost:81/';
        }

        console.log('user added')

    }
}

registerOnClick = () => {
    emailChecker();
    usernameChecker();
    passwordChecker();
    confirmChecker();
    errorChecker();
    if(!hasErrors) {
        //TODO add to database, start session and port over to chatroom
        addUser();
    }
}