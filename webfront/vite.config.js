import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  client: {
    webSocketURL: 'ws://0.0.0.0:9000/',
  },
  headers: {
    'Access-Control-Allow-Origin': '*',

  },
  
  transpileDependencies: true

})
