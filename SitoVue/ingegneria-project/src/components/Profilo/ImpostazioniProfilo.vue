<template>
    <div class="flex flex-col">
        <h1>Impostazioni Profilo</h1>
        <form @submit="saveSettings">
            <label for="name">Nome:</label>
            <input type="text" id="name" v-model="name" required />

            <label for="email">Email:</label>
            <input type="email" id="email" v-model="email" required />

            <label for="password">Password:</label>
            <input type="password" id="password" v-model="password" required />

            <button type="submit">Salva</button>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            name: '',
            email: '',
            password: '',
        };
    },
    methods: {
        saveSettings() {
            // Here you can implement the logic to save the settings
            // For example, you can make an API call to update the user's profile

            // Reset the form fields after saving
            this.name = '';
            this.email = '';
            this.password = '';
        },
    },
};
</script>

<style scoped>
/* Add your custom styles here */
</style>
