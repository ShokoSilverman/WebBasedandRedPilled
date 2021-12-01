const app = require('express')();
const http = require('http').Server(app);
const io = require('socket.io')(http);
const port = process.env.PORT || 81;

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/chatRoom.html');

});

app.get('/registration', (req, res) => {
    res.sendFile(__dirname + '/registration.html');
});

app.get('/:file', (req, res) => {
    res.sendFile(__dirname + '/' + req.params.file);
});

io.on('connection', (socket) => {
    socket.on('chat message', msg => {
        io.emit('chat message', msg);
    });
});

http.listen(port, () => {
    console.log(`Socket.IO server running at http://localhost:${port}/`);
});