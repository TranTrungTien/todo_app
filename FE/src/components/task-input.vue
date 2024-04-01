<script setup>
import { ref } from 'vue'
import { Icon } from '@iconify/vue'

const emit = defineEmits(['onSubmit'])

const taskName = ref('')

const submit = () => {
  emit('onSubmit', taskName.value)
  taskName.value = ''
}
</script>

<template>
  <div class="flex border px-3 py-2 space-x-2 rounded-md mt-2">
    <div class="my-auto">
      <Icon icon="ep:plus" height="20px" width="20px" />
    </div>
    <input
      @keyup.enter="submit"
      v-model="taskName"
      type="text"
      class="focus:outline-none border-none font-light"
      placeholder="Add new task"
    />
  </div>
</template>
