<script setup>
import { cva } from 'class-variance-authority';
import { computed, ref, defineProps, watchEffect } from 'vue';

const props = defineProps({
    name: String,
    src: String,
    initials: String,
    size: {
        type: String,
        validator: (value) => ['small', 'base', 'large'].includes(value),
        default: 'base',
    },
    shape: {
        type: String,
        validator: (value) => ['cerchio', 'square'].includes(value),
        default: 'cerchio',
    },
});

const verifiedSrc = ref(null);
watchEffect(() => {
    const img = new Image();
    img.src = props.src;
    img.decode()
        .then(() => (verifiedSrc.value = props.src))
        .catch((e) => {
            verifiedSrc.value = null;
            throw e;
        });
});

const fallback = computed(() => {
    return props.initials || props.name?.charAt(0) || '?';
});

const containerClass = computed(() => {
    return cva(
        'relative inline-flex items-center justify-center font-bold text-slate-700 select-none shrink-0 bg-primario-300 overflow-hidden',
        {
            variants: {
                size: {
                    small: 'h-12 w-12 text-xs',
                    base: 'h-16 w-16 text-xl',
                    large: 'h-28 w-28 text-5xl',
                },

                shape: {
                    cerchio: 'rounded-full',
                    square: 'rounded',
                },
            },
        }
    )({ size: props.size, shape: props.shape });
});

const innerBorderClass = computed(() => {
    return cva('absolute inset-0 border border-black/5', {
        variants: {
            shape: {
                cerchio: 'rounded-full',
                square: 'rounded',
            },
        },
    })({ shape: props.shape });
});
</script>

<template>
    <span :class="containerClass" :title="props.name">
        <img
            class="h-full w-full object-cover"
            v-if="verifiedSrc"
            :src="verifiedSrc"
            :alt="props.name"
        />

        <template v-else>{{ fallback }}</template>
        <span :class="innerBorderClass"></span>
    </span>
</template>
