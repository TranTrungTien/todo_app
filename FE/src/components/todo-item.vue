<script setup>
import { ref } from 'vue'
import { Icon } from '@iconify/vue'
import { STATUS } from '@/constant/endpoints';
import EditIcon from "@/assets/icons/EditIcon.vue"
import Icons  from "@/components/common/Icon.vue";


const { id, name, todoKey, description, tags, dueDate, startDate, status } = defineProps(['id', 'name', 'todoKey', "description", "status", "startDate", "dueDate", "tags"])

const emit = defineEmits(['onRemove', "onEdit"])

const isCheck = ref(false)

const handleChange = (e) => {
  isCheck.value = e?.target?.checked
}

const handleRemove = () => {
  emit('onRemove', id)
}

const handleEdit = () => {
  emit('onEdit', id)
}

</script>

<template>
  <div class="flex items-center gap-x-2 whitespace-nowrap line-clamp-1">
    <div v-if="!isCheck" @click="() => (isCheck = true)">
      <Icon icon="material-symbols:check-box-outline-blank" height="18px" width="18px" />
    </div>
    <div v-else @click="() => (isCheck = false)">
      <Icon icon="material-symbols:check-box-outline" height="18px" width="18px" />
    </div>
    <input @change="handleChange" :id="id + todoKey" type="checkbox"
      class="w-4 h-4 bg-white border-gray-300 rounded accent-gray-500 hidden" />
    <label :for="id + todoKey" class="ms-2 text-sm font-semibold text-gray-700" :class="{ 'line-through': isCheck }">{{
      name }}</label>
    <div class="text-sm bg-blue-700 font-medium px-2 rounded py-px text-white">{{ STATUS[status] }}</div>
    <div class="text-sm bg-gray-300 font-medium px-2 rounded py-px text-black">Tran Trung Tien</div>
    <div v-for="{ name } in tags" :key="name" class="text-sm bg-orange-600 font-medium px-2 rounded py-px text-white">{{
      name }}</div>
    <div class="flex items-center">
      <div class="text-sm font-medium px-2 rounded py-px">{{ startDate }}</div>
      <span>-</span>
      <div class="text-sm font-medium px-2 rounded py-px">{{ dueDate }}</div>
    </div>
  </div>
  <div class="flex items-center gap-x-2">
    <div @click="handleEdit" class="my-auto h-[18px] w-[18px] grid place-content-center rounded cursor-pointer">
      <Icons :icon="EditIcon" />
    </div>
    <div @click="handleRemove" class="my-auto max-h-[18px] hover:bg-red-500/15 rounded cursor-pointer">
      <Icon icon="material-symbols-light:close" height="18px" width="18px" color="#ff0000" />
    </div>
  </div>
</template>
