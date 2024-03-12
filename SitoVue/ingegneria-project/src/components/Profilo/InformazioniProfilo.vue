<template>
  <div class="flex flex-col gap-3 px-3">
    <div
      class="user-info-photo flex w-full flex-row justify-between items-center bg-slate-100 rounded px-3 py-3 space-x-5"
    >
      <div class="flex flex-row space-x-1">
        <img
          :src="user.picture"
          alt="Profile Picture"
          class="rounded-full w-20 h-20 border-primario-200"
        />
        <div class="User info">
          <h2 class="text-2xl font-semibold mt-4">{{ user.name }}</h2>
          <h2 class="text-sm text-gray-500">{{ user.email }}</h2>
        </div>
      </div>

      <EditButton />
    </div>

    <div class="Bio bg-slate-100 rounded px-3">
      <h1 class="text-xl">Bio:</h1>
      <p>
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Maxime ex delectus obcaecati!
        Molestiae, dicta in error cupiditate, soluta similique cumque deleniti impedit sapiente ex
        itaque mollitia amet dolores perspiciatis. Provident?
      </p>
    </div>

    <div class="Indirizzo px-3 rounded bg-slate-100">
      <h1 class="text-xl">Indirizzo:</h1>
      {{ user.address }}
    </div>

    <ul class="LinkAssociati px-3 rounded bg-slate-100">
      <h1 class="text-xl">Collegamenti</h1>
      <li v-for="(link, index) in linkUtili" v-bind:key="index">
        {{ link }}
      </li>
    </ul>
  </div>
</template>

<script setup>
import { useAuth0 } from '@auth0/auth0-vue'
import EditButton from '@/components/buttons/EditButton.vue'

const linkUtili = ['GitHub', 'LinkedIn', 'Twitter', 'Facebook']

const { user } = useAuth0()
</script>
