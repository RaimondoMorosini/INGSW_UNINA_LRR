<template>
    <Swiper
        :modules="[Autoplay, Pagination, Keyboard, Navigation]"
        :slidesPerView="1"
        :breakpoints="{
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
        }"
        :centered-slides="true"
        :space-between="40"
        :autoplay="{
            delay: 3500,
            disableOnInteraction: false,
        }"
        :pagination="{
            clickable: true,
        }"
        :loop="true"
        :navigation="true"
        :keyboard="true"
        class="bg-primario-100"
    >
        <SwiperSlide v-for="(photo, index) in slides.photo" :key="index" class="p-6">
            <RouterLink :to="{ name: slides.links[index] }">
                <img
                    :src="require(`@/assets/img/carosello/${photo}`)"
                    alt=""
                    class="border-2 border-slate-400"
                />
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
    photo: ['bg1.jpg', 'bg2.jpg', 'bg3.jpg', 'bg4.jpg', 'bg5.jpg', 'bg6.jpg'],
    links: ['hobby', 'perLaCasa', 'elettronica', 'altro', 'abbigliamento', 'sports'],
};
</script>
