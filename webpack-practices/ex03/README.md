ex03: Webpack Development Server

0. Init. Project
$ npm init -y


1. Install Packages
$ npm i -D webpack webpack-cli webpack-dev-server


2. NPM Scripting

  "scripts": {
    "start": "npx webpack serve --progress",
    "build": "npx webpack"
  }


3. Webpack Configuration
  1) entry
  2) output
  3) devServer

    devServer: {
        host: '0.0.0.0',
        port: 9090,
        liveReload: true,
        compress: true,
        hot: false
    } 


4. Build(Bundling)
$ npm run build  


5. Test
$ npm start