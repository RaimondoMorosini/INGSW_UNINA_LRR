<template>
    <div class="relative inline-block">
      <button @click="toggleDropdown" class="dropdown-button px-4 py-2 bg-blue-500 text-white rounded-md">
        Menu
      </button>
      <div v-if="isDropdownVisible" class="dropdown-content absolute left-0 top-full mt-2 bg-white shadow-md rounded-md overflow-hidden z-10">
        <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-100">Link 1</a>
        <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-100">Link 2</a>
        <a href="#" class="block px-4 py-2 text-gray-800 hover:bg-gray-100">Link 3</a>
      </div>
    </div>
  </template>
  
  
  <script setup>
  import { ref, onMounted, onUnmounted } from 'vue';
  
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
  </script>
  
  <style>
  .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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
  </style>
  