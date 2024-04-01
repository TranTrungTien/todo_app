import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { VueQueryPlugin } from '@tanstack/vue-query'

import './index.css'
import './assets/base.css'
import 'element-plus/dist/index.css'

const app = createApp(App)

app.use(VueQueryPlugin)
app.use(router)

app.mount('#app')
