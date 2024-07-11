ex02: Bundling Environment Configuration : webpack.config.js

0. Init. Project
$ npm init -y


1. Install Packages
$ npm i -D webpack webpack-cli express


2. NPM Scripting

  "scripts": {
    "start": "node dev-server",
    "build": "npx webpack"
  }


3. Webpack Configuration
  1) entry
    
    entry: path.resolve('src/index.js')

  2) output

    output: {
      path: path.resolve('public'),
      filename: 'assets/js/main.js'
    }  


4. Build(Bundling)
$ npm run build  


5. Test
$ npm start