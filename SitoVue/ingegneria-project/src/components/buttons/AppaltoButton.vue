<template>
    <Card>
        <template #content>
            <h1>Non riesci a trovare quello che ti interessa? Crea una richiesta</h1>
            <RouterLink :to="{ name: 'creaAppalto' }">
                <Button @click="resetStore" label="Crea richiesta di Appalto" />
            </RouterLink>
        </template>
    </Card>
</template>

<script setup>
import Card from 'primevue/card';
import Button from 'primevue/button';
import { useAstaStore } from '../../stores/astaStore';

const astaStoreInstance = useAstaStore();

function resetStore() {
    deleteAsta();
}
</script>
