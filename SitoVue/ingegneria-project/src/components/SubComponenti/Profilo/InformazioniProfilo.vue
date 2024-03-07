<template>
    <div class="Headder flex w-full flex-row justify-between items-center bg-slate-100 rounded px-3 py-3 space-x-5">
            <div class="user-info-photo flex flex-row space-x-1">
                <img :src="user.picture" alt="Profile Picture" class="rounded-full w-20 h-20 border-primario-200" />
                <div class="User info">
                <h2 class="text-2xl font-semibold mt-4">{{ user.name }}</h2>
                <h2 class="text-sm text-gray-500">{{ user.email }}</h2>
                </div>
            </div>
            
            <EditButton/>
        </div>
</template>

<script setup>
import { useAuth0 } from '@auth0/auth0-vue';
import EditButton from "@/components/buttons/EditButton.vue";

const { user } = useAuth0();


</script>

