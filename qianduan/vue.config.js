module.exports = {
  configureWebpack: {
    resolve: {
      alias:{
        'assets':'@/assets',
        'common':'@/common',
        'components':'@/components',
        'network':'@/network',
        'views':'@/views',
      }
    }
  },
  devServer: {
    port: 8082,
    proxy: {
      'apis':{
        target:'http://localhost:8080',
        changeOrigin: true,

      }
    }
  },
}
