<script setup>
import { computed, defineProps } from 'vue';
import { cva } from 'class-variance-authority';

const props = defineProps({
    IconaSx: Object,
    IconaDx: Object,
    as: {
        type: [String, Object],
        default: 'button',
    },
    intent: {
        type: String,
        validator: (value) =>
            ['primario', 'secondario', 'successo', 'danger', 'warning', 'text'].includes(value),
        default: 'secondario',
    },
});

const menuItemClass = computed(() => {
    return cva(
        'inline-flex items-center justify-center min-h-[32px] px-3 py-o.5 text-sm font-semibold    ',
        {
            variants: {
                intent: {
                    primario: 'bg-primario-500 hover:bg-primario-600',
                    secondario: 'bg-secondario-500 hover:bg-secondario-600',
                    successo: 'bg-successo-500 hover:bg-successo-600',
                    danger: 'bg-danger-500 hover:bg-danger-600',
                    warning: 'bg-warning-500 hover:bg-warning-600',
                    text: 'bg-transparent hover:bg-gray-500 !text-black',
                },
            },
        }
    )({ intent: props.intent });
});
</script>

<template>
    <component :is="props.as" :class="menuItemClass">
        <component :is="IconaSx" />
        <slot></slot>
        <component :is="IconaDx" />
    </component>
</template>
