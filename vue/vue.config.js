const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  //transpileDependencies: true
  //outputDir:"../src/main/resources/static",
  devServer:{
    proxy:{
        '/': {
            target: 'http://localhost:8080',
            changeOrigin: true
        }
    }
  }
})
