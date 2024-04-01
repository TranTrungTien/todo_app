<script setup lang="ts">
import { RouterView } from 'vue-router'
import Sidebar from './components/side-bar.vue'
</script>

<template>
  <div class="min-h-screen flex items-start justify-center">
    <Sidebar />
    <RouterView />
  </div>
</template>
