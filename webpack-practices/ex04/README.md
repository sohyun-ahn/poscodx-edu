ex04: Bundling II: CSS Module


0. Init. Project
$ npm init -y


1. Install Packages
$ npm i -D webpack webpack-cli webpack-dev-server css-loader style-loader


2. NPM Scripting

  "scripts": {
    "start": "npx webpack serve --progress",
    "build": "npx webpack"
  }


3. Webpack Configuration
  1) entry
  2) output
  3) devServer
  4) module
  
    module: {
        rules:[{
            test: /\.css$/i,
            use:['style-loader', 'css-loader']
        }]
    }


4. Build(Bundling)
$ npm run build  


5. Test
$ npm start