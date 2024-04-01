<script setup>
import { defineProps } from 'vue';

const { icon } = defineProps({
    icon: Object
});
</script>

<template >
    <component :is="icon"></component>
</template>