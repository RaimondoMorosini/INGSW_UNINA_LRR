<template>
  <div class="card">
    <DataView
      :value="products"
      layout="grid"
      :sortOrder="sortOrder"
      :itemTemplate="itemTemplate"
      :paginator="true"
      :rows="9"
      :paginatorPosition="'both'"
    >
      <template #itemTemplate="{ data }">
        <div class="p-d-flex p-ai-center p-jc-between">
          <div>
            <img :src="data.image" :alt="data.name" class="product-image" />
            <div class="product-detail">
              <div class="product-name">{{ data.name }}</div>
              <div class="product-description">{{ data.description }}</div>
            </div>
          </div>
          <div class="product-price">
            <span class="product-badge p-text-uppercase">{{ data.price }}</span>
          </div>
        </div>
      </template>
    </DataView>
  </div>
</template>

<script setup>
import DataView from 'primevue/dataview';

import { ref, onMounted } from 'vue';

const products = ref();
const sortKey = ref();
const sortOrder = ref();
const sortField = ref();
const sortOptions = ref([
  { label: 'Price High to Low', value: '!price' },
  { label: 'Price Low to High', value: 'price' },
]);

const onSortChange = (event) => {
  const value = event.value.value;
  const sortValue = event.value;

  if (value.indexOf('!') === 0) {
    sortOrder.value = -1;
    sortField.value = value.substring(1, value.length);
    sortKey.value = sortValue;
  } else {
    sortOrder.value = 1;
    sortField.value = value;
    sortKey.value = sortValue;
  }
};

/*
    onMounted(async () => {
        const response = await fetch('data/products.json');
        const data = await response.json();
        products.value = data;
    });*/
</script>
