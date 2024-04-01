<script setup>
import { getTags, createTask, editTask } from "@/service";
import { useQuery } from '@tanstack/vue-query';
import { ref, watch, toRaw } from 'vue';
import dayjs from "dayjs";
import { ElMessage } from 'element-plus'

const form = ref({
  name: '',
  description: '',
  tag_ids: [],
  assignee: '',
  start_date: new Date(),
  due_date: new Date(),
  active: true,
  status: 0
})

const open = defineModel()
const props = defineProps(['task'])
const emit = defineEmits(['onClose'])

const { data: tags } = useQuery({
  queryKey: ['todos'],
  queryFn: () => getTags(),
  select: (response) => {
    return response.data?.data;
  } // extract data from axios response
})

watch(() => props.task, (task) => {
  const extractProxy = toRaw(task);
  const values = {
    name: extractProxy?.name,
    description: extractProxy?.description,
    tag_ids: extractProxy?.tags?.map(item => item?.id),
    assignee: '',
    start_date: new Date(extractProxy?.start_date),
    due_date: new Date(extractProxy?.due_date),
    active: true,
    status: extractProxy?.status
  }

  form.value = values;
});
 

const handleClose = () => {
  emit('onClose')
}

const handleSaveTask = async() => {
  const params = {...form.value};
  params.due_date = dayjs(params.due_date).format("YYYY-MM-DD");
  params.start_date = dayjs(params.start_date).format("YYYY-MM-DD");
  params.user_id = 1;
  
  let result = null;
  if(props?.task?.id){
    params.id = props?.task?.id,
    result = await editTask(params, params?.id);
  } else result = await createTask(params)

  if (result?.data?.success) ElMessage({
    message: result?.data?.message,
    type: "success"
  });
  else ElMessage({
    message: result?.data?.message,
    type: "error"
  });
  emit('onClose')
}

</script>

<template>
  <el-dialog v-model="open" title="Task" width="500" :before-close="handleClose">
    <el-form :model="form" label-width="auto" style="max-width: 600px">
      <el-form-item label="Name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Description">
        <el-input v-model="form.description" type="textarea" autosize />
      </el-form-item>
      <el-form-item label="Tag">
        <el-select multiple v-model="form.tag_ids">
          <el-option v-for="item in tags" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="Assignee">
        <el-input v-model="form.assignee" />
      </el-form-item> -->
      <el-form-item label="Start Date">
        <el-date-picker v-model="form.start_date" type="date" placeholder="Pick a date" style="width: 100%" />
      </el-form-item>
      <el-form-item label="End Date">
        <el-date-picker v-model="form.due_date" type="date" placeholder="Pick a date" style="width: 100%" />
      </el-form-item>
      <el-form-item label="Active">
        <el-switch v-model="form.active" />
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="handleClose">Cancel</el-button>
        <el-button type="primary" @click="handleSaveTask">Confirm</el-button>
      </div>
    </template>
  </el-dialog>
</template>
