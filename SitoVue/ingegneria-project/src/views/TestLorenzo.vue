<template>
    <FloatLabel variant="on">
        <InputText id="on_label" v-model="value3" />
        <label for="on_label">On Label</label>
    </FloatLabel>
</template>

<script setup>
import { ref } from 'vue';
import FloatLabel from 'primevue/floatlabel';
import InputText from 'primevue/inputtext';
</script>

<style scoped>
.avatar-container {
    position: relative;
    width: fit-content;
}

.image-container {
    height: 200px;
    width: 200px;
    z-index: 1;
    border-radius: 50%;
    background-color: #fff;
    background-position: center;
    background-size: cover;
    box-shadow: 0 10px 20px 20px #e879f91a;
    overflow: hidden;
}

.icon-wrapper {
    position: absolute;
    height: 40px;
    width: 40px;
    padding: 0.35rem;
    background-color: #e879f9;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    right: 0;
    top: 75%;
    z-index: 2;
    cursor: pointer;

    &:hover {
        transition: all 0.25s;
        background-color: darken(#e879f9, 15%);
    }

    .icon {
        color: white;
    }

    label {
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    input[type='file'] {
        display: none;
    }
}

.preview {
    max-width: 100%;
    max-height: 300px;
    margin-top: 10px;
}
.textarea {
    resize: none;
}
</style>
