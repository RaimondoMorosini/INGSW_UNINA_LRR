<template>
    <li v-for="(categoria, index) in categorie" :key="index">
        {{ categoria.nome }}
        <ul v-if="categoria.figlie.length">
            <li v-for="(subCategoria, indexSub) in categorie.figlie" :key="indexSub">{{ subCategoria.nome }}</li>
            <ricorsione :categorie="categoria.figlie" />
        </ul>
    </li>
</template>
  
<script>
export default {
    name: 'Ricorsione',
    props: {
        categorie: {
            type: Array,
            required: true
        }
    }
};
</script>