<script setup>
import { computed } from 'vue';
import { Listbox, ListboxButton, ListboxOption, ListboxOptions } from '@headlessui/vue';
import { CheckIcon, SelectorIcon } from '@heroicons/vue/solid';

const props = defineProps({
  options: Array,
  modelValue: [String, Number, Array],
  placeholder: {
    type: String,
    default: 'Select option',
  },
  multiple: Boolean,
  error: String,

  as: {
    type: [String, Object],
    default: 'template',
  },
});

const emit = defineEmits(['update:modelValue']);

const label = computed(() => {
  return props.options
    .filter((option) => {
      if (Array.isArray(props.modelValue)) {
        return props.modelValue.includes(option.value);
      }

      return props.modelValue === option.value;
    })
    .map((option) => option.label)
    .join(', ');
});
</script>
<template>
  <Listbox
    :model-value="props.modelValue"
    :multiple="props.multiple"
    @update:modelValue="(value) => emit('update:modelValue', value)"
  >
    <div class="relative mt-1">
      <ListboxButton
        class="relative w-full cursor-default rounded-lg bg-white py-2 pl-3 pr-10 text-left shadow-md focus:outline-none focus-visible:border-primario-500 focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300 sm:text-sm"
      >
        <span v-if="label" class="block truncate">{{ label }}</span>
        <span v-else class="text-gray-500">{{ props.placeholder }}</span>
        <span class="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
          <SelectorIcon aria-hidden="true" class="h-5 w-5 text-gray-400" />
        </span>
      </ListboxButton>

      <transition
        leave-active-class="transition duration-100 ease-in"
        leave-from-class="opacity-100"
        leave-to-class="opacity-0"
      >
        <ListboxOptions
          class="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm"
        >
          <ListboxOption
            v-for="option in props.options"
            :key="option.label"
            v-slot="{ active, selected }"
            :value="option.value"
            as="props.as"
          >
            <li
              :class="[
                active ? 'bg-primario-100/30 text-primario-900' : 'text-gray-900',
                'relative cursor-default select-none py-2 pl-10 pr-4',
              ]"
            >
              <span :class="[selected ? 'font-medium' : 'font-normal', 'block truncate']">{{
                option.label
              }}</span>
              <span
                v-if="selected"
                class="absolute inset-y-0 left-0 flex items-center pl-3 text-amber-600"
              >
                <CheckIcon aria-hidden="true" class="h-5 w-5" />
              </span>
            </li>
          </ListboxOption>
        </ListboxOptions>
      </transition>
      <div class="mt-1 text-xs text-danger-400" v-if="props.error">{{ props.error }}</div>
    </div>
  </Listbox>
</template>
