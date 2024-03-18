<template>
  <div class="card">
    <div class="border-round border-1 surface-border surface-card p-4">
      <div class="mb-3 flex">
        <Skeleton shape="circle" size="4rem" class="mr-2"></Skeleton>
        <div>
          <Skeleton width="10rem" class="mb-2"></Skeleton>
          <Skeleton width="5rem" class="mb-2"></Skeleton>
          <Skeleton height=".5rem"></Skeleton>
        </div>
      </div>
      <Skeleton width="100%" height="150px"></Skeleton>
      <div class="justify-content-between mt-3 flex">
        <Skeleton width="4rem" height="2rem"></Skeleton>
        <Skeleton width="4rem" height="2rem"></Skeleton>
      </div>
    </div>
  </div>
</template>

<script setup>
import Skeleton from 'primevue/skeleton';
</script>
