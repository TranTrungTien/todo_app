<script setup>
import { ref } from 'vue';

import { useQuery } from "@tanstack/vue-query";

import { endpoints } from '@/constant/endpoints';
import { deleteTask, getTasks } from "@/service";
import { Icon } from '@iconify/vue';
import TaskModal from './modal/task-modal.vue';
import TodoItem from './todo-item.vue';
import { ElMessage } from 'element-plus'


const MAX_TODO = 8


const { title, containerKey } = defineProps(['title', 'containerKey'])

const openTaskModal = ref({ open: false, data: null })

const { data, isFetching, refetch } =
  useQuery({
    queryKey: [endpoints.TASK_CURD, "GET"],
    queryFn: async() => {
      const result = await getTasks();
      return result?.data?.data;
    },
    keepPreviousData: true,
  });

const handleRemove = async(id) => {
  const result = await deleteTask(id);

  if (result?.data?.success) ElMessage({
    message: result?.data?.message,
    type: "success"
  });
  else ElMessage({
    message: result?.data?.message,
    type: "error"
  });
  refetch();
}

const handleEdit = (id) => {
  const task = data?.value.find(item => item?.id === id);
  openTaskModal.value.data = task;
  openTaskModal.value.open = true;
}

const handleCloseModal = () => {
  openTaskModal.value.open = false;
  refetch();
}
</script>

<template>
  <div class="p-4 border rounded-lg space-y-2">
    <div class="flex items-center justify-between">
      <div class="text-xl font-medium">{{ title }}</div>
      <el-button @click="openTaskModal.open = true" type="primary">
        <Icon icon="ep:plus" height="18px" width="18px" color="#ffffff" />
        <span class="text-base ml-1">Task</span>
      </el-button>
    </div>
    <div v-if="data?.length" class="min-h-[300px]">
      <div v-for="{ id, name, status, description, start_date, due_date, tags } in data" :key="id"
        class="flex items-center justify-between p-3 border-t-0 border-r-0 border-l-0 border-b border-solid border-gray-100 hover:bg-gray-100 rounded cursor-pointer space-x-3">
        <TodoItem :description="description" :startDate="start_date" :status="status" :dueDate="due_date" :tags="tags" :id="id"
          :name="name" :todoKey="containerKey" @onRemove="handleRemove" @onEdit="handleEdit"/>
      </div>
    </div>
    <el-empty v-else description="No Data" />
    <!-- <div v-if="todos.length > MAX_TODO" class="mt-3 w-full flex justify-end">
      <span class="text-sm underline text-blue-500">Show more</span>
    </div> -->
  </div>
  <TaskModal v-model="openTaskModal.open" :task="openTaskModal.data" @onClose="handleCloseModal" />
</template>
