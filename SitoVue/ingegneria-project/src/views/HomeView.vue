<!-- eslint-disable prettier/prettier -->
<template>
  <SwipeCarosello />

  <div
    class="AreaRicerca flex min-w-full flex-col items-center gap-3 px-5 py-3 duration-500 ease-in md:flex md:w-auto md:flex-row md:items-center"
  >
    <InputGroup>
      <InputGroupAddon class="bg-primario-100">
        <i class="pi pi-search"></i>
      </InputGroupAddon>
      <InputText
        v-model="value"
        placeholder="Search"
        class="md:w-14rem min-w-2col rounded-r border-transparent bg-primario-100/50 hover:ring-primario-300 focus:ring-primario-200"
      >
        <template #imputtexticon>
          <i class="pi pi-search" style="color: #424242"></i>
        </template>
      </InputText>
    </InputGroup>

    <InputGroup>
      <InputGroupAddon class="bg-primario-100">
        <i class="pi pi-th-large" style="color: #424242"></i>
      </InputGroupAddon>
      <TreeSelect
        v-model="selectedCategory"
        :options="cities"
        option-label="name"
        placeholder="Seleziona Categoria"
        class="!hover:ring-primario-300 !focus:ring-primario-300 md:w-14rem min-w-2col rounded-r bg-primario-100/50 text-black"
      />
    </InputGroup>

    <InputGroup>
      <InputGroupAddon class="bg-primario-100">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20">
          <g id="asta">
            <path
              d="M19.0096 9.61639L17.7596 8.36639C17.5476 8.15463 17.2665 8.02607 16.9677 8.00413C16.6689 7.98219 16.3721 8.06833 16.1314 8.24686L12.2533 4.36873C12.4319 4.12817 12.5182 3.83144 12.4964 3.53261C12.4746 3.23377 12.3462 2.95271 12.1346 2.74061L10.8846 1.49061C10.7685 1.3745 10.6307 1.2824 10.479 1.21957C10.3273 1.15673 10.1647 1.12439 10.0006 1.12439C9.8364 1.12439 9.67383 1.15673 9.52215 1.21957C9.37048 1.2824 9.23266 1.3745 9.11659 1.49061L4.11659 6.49061C4.00048 6.60668 3.90838 6.7445 3.84555 6.89617C3.78271 7.04785 3.75037 7.21042 3.75037 7.37459C3.75037 7.53877 3.78271 7.70134 3.84555 7.85301C3.90838 8.00469 4.00048 8.1425 4.11659 8.25858L5.36659 9.50858C5.57864 9.72001 5.85955 9.8483 6.15821 9.87009C6.45687 9.89189 6.75343 9.80574 6.99393 9.62733L7.55409 10.1875L2.44627 15.2953C2.10693 15.6664 1.92382 16.1542 1.93506 16.6569C1.9463 17.1597 2.15103 17.6387 2.50663 17.9943C2.86222 18.3499 3.34127 18.5546 3.84403 18.5659C4.34679 18.5771 4.83452 18.394 5.20565 18.0547L10.3127 12.9469L10.8728 13.5062C10.6942 13.7468 10.6079 14.0435 10.6297 14.3424C10.6515 14.6412 10.7799 14.9223 10.9916 15.1344L12.2416 16.3844C12.3577 16.5005 12.4955 16.5926 12.6472 16.6554C12.7988 16.7182 12.9614 16.7506 13.1256 16.7506C13.2897 16.7506 13.4523 16.7182 13.604 16.6554C13.7557 16.5926 13.8935 16.5005 14.0096 16.3844L19.0096 11.3844C19.1257 11.2683 19.2178 11.1305 19.2806 10.9788C19.3434 10.8271 19.3758 10.6646 19.3758 10.5004C19.3758 10.3362 19.3434 10.1736 19.2806 10.022C19.2178 9.87028 19.1257 9.73247 19.0096 9.61639ZM6.25018 8.62498L5.00018 7.37498L10.0002 2.37498L11.2502 3.62498L6.25018 8.62498ZM4.32206 17.1703C4.19032 17.3013 4.01211 17.3748 3.82635 17.3748C3.6406 17.3748 3.46239 17.3013 3.33065 17.1703C3.19993 17.0386 3.12657 16.8605 3.12657 16.675C3.12657 16.4894 3.19993 16.3114 3.33065 16.1797L8.43768 11.0719L9.42909 12.0625L4.32206 17.1703ZM7.89081 8.75467L11.3799 5.26561L15.2346 9.1203L11.7463 12.6094L7.89081 8.75467ZM13.1252 15.5L11.8752 14.25L12.1877 13.9375L16.5627 9.56248L16.8752 9.24998L18.1252 10.5L13.1252 15.5Z"
              fill="#424242"
            />
          </g>
        </svg>
      </InputGroupAddon>
      <TreeSelect
        v-model="selectedCity1"
        :options="cities"
        option-label="name"
        placeholder="Seleziona Città"
        class="!hover:ring-primario-300 !focus:ring-primario-300 md:w-14rem min-w-2col rounded-r bg-primario-100/50 text-black"
      />
    </InputGroup>
  </div>

  <div>
    <Vetrina />
  </div>
</template>

<script setup>
import SwipeCarosello from '../components/SwipeCarosello.vue'

import TreeSelect from 'primevue/treeselect'
import InputText from 'primevue/inputtext'

import InputGroup from 'primevue/inputgroup'
import InputGroupAddon from 'primevue/inputgroupaddon'

import Vetrina from '@/components/Vetrina.vue'

import { ref, onMounted } from 'vue'

const value = ref(null)
const selectedCategory = ref()
const selectedCity1 = ref()

onMounted(() => {
  //TODO aggiugere dati per TreeSelect
})

const cities = ref([
  { name: 'New York', code: 'NY' },
  { name: 'Rome', code: 'RM' },
  { name: 'London', code: 'LDN' },
  { name: 'Istanbul', code: 'IST' },
  { name: 'Paris', code: 'PRS' }
])
</script>
