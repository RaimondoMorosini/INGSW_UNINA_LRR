<template>
  <div class="flex items-center">
    <input
      v-bind="$attrs"
      :checked="modelValue === value"
      @change="updateInput"
      :value="value"
      type="radio"
      class="h-4 w-4 focus:ring-0"
    />
    <label class="ml-2 block text-sm text-gray-900">
      {{ label }}
    </label>
  </div>
</template>

<script setup>
const emit = defineEmits(['update:modelValue']);

const props = defineProps({
  label: {
    type: String,
    default: '',
  },
  modelValue: {
    type: [String, Number],
    default: '',
  },
  value: {
    type: [String, Number],
    required: true,
  },
});

const updateInput = ($event) => {
  emit('update:modelValue', $event.target.value);
};
</script>
