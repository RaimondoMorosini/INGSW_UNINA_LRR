<template>
    <div class="content-layout">
      <h1 id="page-title" class="content__title">Profile Page</h1>
      <div class="content__body">
        <p id="page-description">
          <span
            >You can use the <strong>ID Token</strong> to get the profile
            information of an authenticated user.</span
          >
          <span
            ><strong
              >Only authenticated users can access this page.</strong
            ></span
          >
        </p>
        <div class="profile-grid">
          <div class="profile__header">
            <img :src="user.picture" alt="" class="profile__avatar rounded overflow-hidden min-h-1col min-w-1col border-2 border-primario-100" />
            <div class="profile__headline">
              <h2 class="profile__title">{{ user.name }}</h2>
              <span class="profile__description">{{ user.email }}</span>
            </div>
          </div>
          <div class="profile__details">
            <CodeSnippet title="Decoded ID Token" :code="code" />
            <h1>
              {{ code }}
            </h1>
          </div>
        </div>
      </div>
    </div>

</template>

<script setup>
import CodeSnippet from "@/components/code-snippet.vue";
import { useAuth0 } from "@auth0/auth0-vue";

const { user } = useAuth0();

const code = user ? JSON.stringify(user.value, null, 2) : "";
</script>