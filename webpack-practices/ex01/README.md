ex01: Bundling I: JS Module

0. Init. Project
$ npm init -y

1. Install Packages
$ npm i -D webpack webpack-cli express

2. NPM Scripting

  "scripts": {
    "start": "node dev-server",
    "build": "npx webpack ./src/index.js -o ./public"
  }

3. Build(Bundling)
$ npm run build  

4. Test
$ npm start
