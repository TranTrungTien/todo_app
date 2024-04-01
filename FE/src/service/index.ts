import API from '@/config/APIClient'
import { endpoints } from '@/constant/endpoints'

export const getTags = () => {
  return API.get(endpoints.GET_TAGS)
}

export const createTask = (params: any) => {
  return API.post(endpoints.TASK_CURD, params)
}

export const editTask = (params: any, id: number) => {
  return API.put(`${endpoints.TASK_CURD}/${id}`, params)
}

export const getTasks = (params: any) => {
  return API.get(endpoints.TASK_CURD, params)
}

export const deleteTask = (id: number) => {
  return API.delete(`${endpoints.TASK_CURD}/${id}`)
}
