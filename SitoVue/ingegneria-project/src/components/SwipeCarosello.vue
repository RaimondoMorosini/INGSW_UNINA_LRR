<template>
  <Swiper
    :modules="[Autoplay, Pagination, Keyboard, Navigation]"
    :slidesPerView="1"
    :breakpoints="{
      '640': {
        slidesPerView: 2,
        spaceBetween: 20
      },
      '768': {
        slidesPerView: 3,
        spaceBetween: 40
      },
      '1024': {
        slidesPerView: 3,
        spaceBetween: 50
      }
    }"
    :centeredSlides="true"
    :spaceBetween="40"
    :autoplay="{
      delay: 3500,
      disableOnInteraction: false
    }"
    :pagination="{
      clickable: true
    }"
    :loop="true"
    :navigation="true"
    :keyboard="true"
    class="bg-primario-100 !px-10 !py-6 lg:h-6col"
  >
    <SwiperSlide v-for="(photo, index) in slides.photo" :key="index">
      <RouterLink :to="{ name: slides.links[index] }">
        <img :src="require(`@/assets/${photo}`)" alt="" class="border-2 border-slate-400" />
      </RouterLink>
    </SwiperSlide>
  </Swiper>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { Swiper, SwiperSlide } from 'swiper/vue'
import { Autoplay, Pagination, Keyboard, Navigation } from 'swiper/modules'
// Import Swiper styles
import 'swiper/css'
import 'swiper/css/navigation'
import 'swiper/css/pagination'
import 'swiper/css/effect-cube'
const slides = {
  photo: ['bg1.jpg', 'bg2.jpg', 'bg3.jpg', 'bg4.jpg', 'bg5.jpg', 'bg6.jpg'],
  links: ['hobby', 'perLaCasa', 'elettronica', 'altro', 'abbigliamento', 'sports']
}
</script>
