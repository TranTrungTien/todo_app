<script setup>
import TodoContainer from '@/components/todo-container.vue'
</script>

<template>
  <main class="p-4 flex-1">
    <div class="flex items-center gap-x-2 mb-2">
      <h1 class="text-4xl font-medium">Upcoming</h1>
      <div class="border w-10 h-10 grid place-content-center rounded font-medium text-lg">12</div>
    </div>
    <!-- today -->
    <div class="p-2">
      <TodoContainer title="Today" containerKey="today" />
      <!-- tomorrow -->
      <div class="grid grid-cols-2 gap-4">
        <TodoContainer title="Tomorrow" containerKey="tomorrow" />

        <!-- this week -->
        <TodoContainer title="This week" containerKey="this_week" />
      </div>
    </div>
  </main>
</template>
../components/todo-item.vue
