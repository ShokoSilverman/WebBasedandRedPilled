//const express = require('express'); had to do these to get them installed, there is not a simple npm install window -_-
//const {MongoClient, ObjectId} = require("mongodb");
// const http = require('http');
// const { Server } = require("socket.io");

let testOutVal = ''

testInput = () => {
    let userIn = document.querySelector('#input').value
    console.log(userIn);
    testOutVal+= userIn + "\n";
    document.getElementById('testOut').innerHTML = testOutVal;
}