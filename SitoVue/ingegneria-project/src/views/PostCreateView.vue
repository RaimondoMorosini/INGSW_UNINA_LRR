<script setup>
import axios from '@/lib/axios';
import useForm from '@/composables/form';
import { useRouter } from 'vue-router';

const router = useRouter();

const form = useForm({
    title: '',
    content: '',
});

async function handleSubmit() {
    await form.submit(async (fields) => {
        await axios.post('/api/posts', fields);
        await router.push('/');
    });
}
</script>

<template>
    <main>
        <h1>Create post</h1>

        <p class="text-red-500" v-if="form.error?.message">
            {{ form.error.message }}
        </p>

        <form class="space-y-2" @submit.prevent="handleSubmit">
            <div class="flex flex-col">
                <label for="title">Title</label>
                <input type="text" v-model="form.fields.title" id="title" />
                <span class="text-red-500" v-if="form.error?.validation.title">
                    {{ form.error?.validation.title }}
                </span>
            </div>
            <div class="flex flex-col">
                <label for="content">Content</label>
                <textarea rows="4" v-model="form.fields.content" id="content"></textarea>
                <span class="text-red-500" v-if="form.error?.validation.content">
                    {{ form.error?.validation.content }}
                </span>
            </div>
            <div>
                <button class="rounded bg-blue-500 px-3 py-1 hover:bg-blue-600">
                    {{ form.processing ? 'Creating...' : 'Create' }}
                </button>
            </div>
        </form>
    </main>
</template>
