<template>
    <Dialog
        header="Sessione Scaduta"
        :visible="visible"
        :modal="true"
        :closable="false"
        @hide="onHide"
    >
        <div class="dialog-content">
            <h4>Attenzione!</h4>
            <p>
                La tua sessione è scaduta per motivi di sicurezza. Per favore, effettua nuovamente
                l'accesso per continuare a utilizzare il nostro servizio.
            </p>
            <p>In alternativa, puoi scegliere di effettuare il logout.</p>
        </div>
        <div class="dialog-footer">
            <LoginButton class="p-button-success" />
            <LogOutButton @click="logout" class="p-button-danger" />
        </div>
    </Dialog>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';
import LoginButton from './buttons/LoginButton.vue';
import LogOutButton from './buttons/LogOut.vue';
const props = defineProps({
    visible: {
        type: Boolean,
        required: true,
    },
});

const emit = defineEmits(['update:visible']);

const onHide = () => {
    emit('update:visible', false);
};

const refaiAccesso = () => {
    console.log('Rifare accesso');
    onHide();
    // Qui puoi reindirizzare l'utente alla pagina di accesso
};

const logout = () => {
    console.log('Logout effettuato');
    localStorage.removeItem('access_token'); // Rimuovi il token
    onHide();
    // Qui puoi reindirizzare l'utente alla pagina principale
};
</script>

<style>
.dialog-content {
    text-align: center;
    margin: 20px 0;
}

.dialog-footer {
    display: flex;
    justify-content: space-around;
}
</style>
