<template>
  <div class="relative inline-block">
    <button
      @click="toggleDropdown"
      class="dropdown-button rounded-md bg-blue-500 px-4 py-2 text-white"
    >
      Menu
    </button>
  </div>

  <div>
    <ul>
      <Ricorsione :categorie="categorie" />
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, defineProps, defineEmits } from 'vue';
import categorie from '../script/getCategorie.js';

const isDropdownVisible = ref(false);

function toggleDropdown() {
  isDropdownVisible.value = !isDropdownVisible.value;
}

function closeDropdown() {
  isDropdownVisible.value = false;
}

function handleClickOutside(event) {
  if (!event.target.closest('.relative')) {
    closeDropdown();
  }
}

onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside);
});

////////////////codice per le categorie//////////////////////////////////////////////////////////////////
</script>

<script>
import Ricorsione from './Ricorsione.vue';

export default {
  name: 'App',
  components: {
    Ricorsione,
  },
};
</script>

<style>
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-button:focus + .dropdown-content,
.dropdown-content:hover {
  display: block;
}

/*Menu a tendina-----------------------------------------------*/
</style>
