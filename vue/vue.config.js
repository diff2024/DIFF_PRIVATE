module.exports = {
  outputDir: "../src/main/resources/static",  
  indexPath: "../static/index.html",
  lintOnSave: false,
  devServer: {  
    proxy: "http://localhost:8080",
    disableHostCheck: true,
  },
  transpileDependencies: [
    'vuetify'
  ]
}
