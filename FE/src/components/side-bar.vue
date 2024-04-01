<script setup>
import { Icon } from '@iconify/vue'
</script>

<template>
  <div class="min-w-[18rem] max-w-[20rem] h-screen self-start p-4 sticky top-0 left-0">
    <div class="bg-gray-100 w-full h-full rounded-md">
      <div class="flex items-center justify-between px-4 py-2">
        <p class="font-medium text-lg">Menu</p>
        <div>
          <Icon icon="pajamas:hamburger" color="#000" />
        </div>
      </div>
      <div class="px-3">
        <div class="flex items-center gap-x-2 rounded-lg bg-gray-50 border py-2 px-2 pl-3">
          <div class="max-h-[24px]">
            <Icon icon="material-symbols-light:search" color="#000" class="text-2xl" />
          </div>
          <input
            type="text"
            name=""
            id=""
            class="focus:outline-none outline-none w-full bg-transparent border-none"
            placeholder="Search"
          />
        </div>
      </div>
      <div class="px-4 mt-4">
        <div class="font-medium">Tasks</div>
        <div
          class="mt-2 flex items-center justify-between bg-gray-200 rounded-md w-full px-1 py-[5px]"
        >
          <div class="flex justify-start items-center gap-x-2">
            <div>
              <Icon icon="material-symbols-light:keyboard-double-arrow-right" class="text-2xl" />
            </div>
            <p class="text-sm font-medium">Upcoming</p>
          </div>
          <div
            class="w-8 h-8 grid place-content-center bg-slate-100 border text-sm font-medium rounded-md"
          >
            12
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
