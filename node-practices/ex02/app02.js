var http = require("http");
var fs = require("fs");

var PORT = 9090;

var server = http.createServer(function (req, res) {
  console.log(req.url);
  if (req.url === "/") {
    req.url = "/index.html";
  }

  fs.readFile(__dirname + "/public" + req.url, function (err, data) {
    // __dirname : 현재 direactory name이 뜸 (상수)

    res.writeHead(200, {
      "Content-Type": "text/html",
    });
    res.end(data); // end : body 적어줌
  });
});

server.listen(PORT, function () {
  console.log("Server running at http://localhost:" + PORT + "/");
});
