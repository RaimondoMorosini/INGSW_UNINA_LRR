<template>
    <div></div>
</template>

<script>
import axios from 'axios';

export default {
    mounted() {
        axios
            .post('http://localhost:8081/auth/login', {
                email: '3roby3@gmail.com',
                password: '123',
                metodoDiRegistrazione: 'Dieti',
            })
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.error('Errore durante la richiesta:', error);
            });
    },
};
</script>
