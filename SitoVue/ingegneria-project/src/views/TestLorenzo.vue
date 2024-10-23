<template>
    <div>
      <Toast position="top-left" group="tl"/>
      <h1>Toast Service Example</h1>
      <Button label="Show Toast" @click="showTopLeft" />
    </div>
  </template>
  
  <script setup>
  import Toast from 'primevue/toast'
  import { useToast } from 'primevue/usetoast'; // Import the useToast hook
  import Button from 'primevue/button';
  const toast = useToast();

  const showTopLeft = () => {
    toast.add({ severity: 'info', summary: 'Info Message', detail: 'Message Content', group: 'tl', life: 3000 });
};
  </script>
  