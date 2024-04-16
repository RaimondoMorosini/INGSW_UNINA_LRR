<template>
  <Steppy
    v-model:step="step"
    :loading="loading"
    :finalize="finalize"
    :circleSize="35"
    :backgroundColor="coloreBG"
    :primaryColor1="coloreMain"
    :primaryColor2="coloreSecondario"
    :backText="`Indietro`"
    :nextText="`Avanti`"
    :finalizeText="`Conferma`"
  >
    <template #1>
      <CreaProdotto />
    </template>
    <template #2
      >Vivamus porttitor sodales ipsum in interdum. Proin suscipit ac nunc a tristique. Curabitur et
      pharetra diam, nec porttitor risus. Curabitur vel mauris est. In tempor nibh sed dapibus
      tincidunt. Donec id pharetra nunc, at rhoncus dui. Nulla in feugiat ante. Maecenas euismod
      justo eget dignissim faucibus. Nam elementum dolor sit amet felis sodales luctus. Aenean et
      lacus varius, mollis mi nec, posuere lectus. Pellentesque tempor sapien a magna fringilla
      iaculis.</template
    >
    <template #3
      >Etiam in turpis erat. Vestibulum auctor vel nibh eget vehicula. Integer finibus libero a ex
      vehicula pharetra. Suspendisse dapibus ipsum elit, nec euismod sapien laoreet ac. Proin ex
      arcu, commodo nec faucibus ac, pellentesque sed purus. Maecenas vitae ullamcorper nunc. Fusce
      dapibus lacinia dolor non tristique. Sed eu volutpat enim. Donec nisi nulla, eleifend quis
      mollis eu, posuere nec lorem. Donec id libero ex. Aliquam ut massa dolor.</template
    >
  </Steppy>
</template>

<script setup>
import { ref } from 'vue';
import { Steppy } from 'vue3-steppy';
import CreaProdotto from '../components/stepper/formCreaProdotto.vue';

//const coloreBG = '#f1f5f9';
const coloreMain = '#cd87f6';
const coloreSecondario = '#f1f5f9';

const step = ref(1);
const loading = ref(false);

const finalize = () => {
  loading.value = true;
};
</script>

<style scoped></style>
