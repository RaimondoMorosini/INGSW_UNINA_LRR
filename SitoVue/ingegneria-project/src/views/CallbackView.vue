<template>
    <div class="card">
        <div class="border-round border-1 surface-border surface-card p-4">
            <div class="mb-3 flex">
                <Skeleton shape="circle" size="4rem" class="mr-2"></Skeleton>
                <div>
                    <Skeleton width="10rem" class="mb-2"></Skeleton>
                    <Skeleton width="5rem" class="mb-2"></Skeleton>
                    <Skeleton height=".5rem"></Skeleton>
                </div>
            </div>
            <Skeleton width="100%" height="150px"></Skeleton>
            <div class="justify-content-between mt-3 flex">
                <Skeleton width="4rem" height="2rem"></Skeleton>
                <Skeleton width="4rem" height="2rem"></Skeleton>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useProfiloStore } from '../stores/profiloStore.js';

import { useAuth0 } from '@auth0/auth0-vue';
import Skeleton from 'primevue/skeleton';
import { onUnmounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { inserisciDato } from '../scripts/DatiUtils';
import { postRest } from '../scripts/RestUtils';

const { idTokenClaims, isAuthenticated, user } = useAuth0();
const router = useRouter();
const route = useRoute();
onUnmounted(async () => {
    try {
        if (!isAuthenticated.value) {
            console.error('User is not authenticated');
            router.push({ name: 'home' });
        }
        //chiamata post per ottenere il token dati richiesti: public record CredenzialiUtenteDTO(String email, String password,String metodoDiRegistrazione) {
        const bodyData = {
            email: idTokenClaims.value.email,
            password: idTokenClaims.value.__raw,
            metodoDiRegistrazione: 'auth0',
        };

        const token = await postRest('auth/login', bodyData);
        if (token) {
            console.log('token ottenuto', token);
            inserisciDato('token', token);
        } else {
            console.error('token non ottenuto');
            router.push({ name: 'home' });
        }

        console.log('access token', JSON.stringify(idTokenClaims.value.__raw));
    } catch (error) {
        console.error('errore getting token ', error);
        router.push({ name: 'home' });
    }
});

console.log('isAuthenticated', isAuthenticated.value);
</script>
