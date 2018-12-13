//port: 8100

var http = require('http').createServer(handler); //require http server, and create server with function handler()
var fs = require('fs'); //require filesystem module
var io = require('socket.io')(http) //require socket.io module and pass the http object (server)
var exec = require("child_process").exec;

http.listen(8200); //listen to port 8200

function handler (req, res) { //create server
  fs.readFile(__dirname + '/public/cctv_control.html', function(err, data) { //read file index.html in public folder
    if (err) {
      res.writeHead(404, {'Content-Type': 'text/html'}); //display 404 on error
      return res.end("404 Not Found");
    } 
    res.writeHead(200, {'Content-Type': 'text/html'}); //write HTML
    res.write(data); //write data from index.html
    return res.end();
  });
}

 io.sockets.on('connection', function (socket) {// WebSocket Connection
                        
    console.log("Client connected");  

    socket.on('button_center', function(data) { //get light switch status from client
          console.log("button_center");
          exec("python camera_move.py", function (err, stdout, stderr) {
                     console.log("Move Camera position to Center " + stdout);
          });                               
    });

    socket.on('button_updown', function(data) { //get light switch status from client
          //console.log("button_updown: position, ", data);
          exec("python camera_move.py -y " + data, function (err, stdout, stderr) {
              console.log("Move Camera position y to " + data + ", " + stdout);
          });  
    });

    socket.on('button_rightleft', function(data) { //get light switch status from client
          //console.log("button_rightleft: position, ", data);
          exec("python camera_move.py -x " + data, function (err, stdout, stderr) {
              console.log("Move Camera position x to " + data + ", " + stdout);
          });                                           
    });                                                

});

process.on('SIGINT', function () { //on ctrl+c
 
  process.exit(); //exit completely
});