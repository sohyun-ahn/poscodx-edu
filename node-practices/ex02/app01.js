var http = require("http");

var PORT = 9090;

var server = http.createServer(function (req, res) {
  res.writeHead(200, {
    "Content-Type": "text/html",
  });

  res.end("<h1>Hello, world!</h1>");
});

server.listen(PORT, function () {
  console.log("Server running at http://localhost:" + PORT + "/");
});
