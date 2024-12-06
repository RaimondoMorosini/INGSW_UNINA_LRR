<template>
    <Swiper style="--swiper-navigation-color: #1c1b22; --swiper-pagination-color: #1c1b22"
        :modules="[Autoplay, Pagination, Keyboard, Navigation]" :slidesPerView="1" :breakpoints="{
            '640': {
                slidesPerView: 2,
                spaceBetween: 20,
            },
            '768': {
                slidesPerView: 3,
                spaceBetween: 30,
            },
            '1024': {
                slidesPerView: 3,
                spaceBetween: 20,
            },
        }" :centered-slides="true" :space-between="40" :autoplay="{
            delay: 3500,
            disableOnInteraction: false,
        }" :pagination="{
            clickable: true,
        }" :loop="true" :navigation="true" :keyboard="true" class="bg-primario-400">
        <SwiperSlide v-for="(photo, index) in slides.photo" :key="index" class="p-6">
            <RouterLink :to="{
                name: 'aste',
                query: {
                    pagina: 1,
                    categoria: slides.links[index],
                    tipoAsta: JSON.stringify([]),
                    lista: JSON.stringify([]),
                },
            }">
                <img :src="require(`@/assets/img/carosello/${photo}`)" alt="" class="border-greyButton-400 border-2" />
            </RouterLink>
        </SwiperSlide>
    </Swiper>
</template>

<script setup>
import { Autoplay, Keyboard, Navigation, Pagination } from 'swiper/modules';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { RouterLink } from 'vue-router';
// Import Swiper styles
import 'swiper/css';
import 'swiper/css/effect-cube';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

const slides = {
    photo: ['bg1.jpg', 'bg2.jpg', 'bg3.jpg','bg4.webp'],
    links: [
        'Sport e Hobby',
        'Motori',
        'Per la casa e la persona',
        'Elettronica',
    ],
};
</script>
