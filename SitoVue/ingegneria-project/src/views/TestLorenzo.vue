<template>
   <Swiper :modules="[EffectCube,Pagination,Keyboard]" 
   :slides-per-view="3"
   :pagination="{ clickable: true }"
   :keyboard="{ enabled: true }"
   effect="cube"
   :grabCursor="true" 
   :loop="true"
   :cubeEffect="{
    shadow:true,
    slideShadow:true,
    shadowOffset:20,
    shadowScale:0.94
   }"
   class="h-5col w-full bg-indigo-200">
        <SwiperSlide v-for="photo in photos" :key="photo">
             <img :src="require(`@/assets/${photo}`)" alt="" class="w-[100%] h-[100%]"/>
        </SwiperSlide>
    </Swiper>
    <div class="flex justify-center items-center">
    test</div>
    <ColorPicker class="pl-5 " v-model="color" inputId="cp-hex" format="hex"></ColorPicker>
        <Button label="Save" class=" hover:bg-green-400" />
</template>


<script setup>
    import ColorPicker from 'primevue/colorpicker';
    import Button from 'primevue/button';
    import { ref } from 'vue';
    import { Swiper, SwiperSlide } from 'swiper/vue';
    import {EffectCube,Pagination,Keyboard} from 'swiper/modules'; 
    // Import Swiper styles
    import 'swiper/css';
    import "swiper/css/effect-cube"
    const photos = ["bg1.jpg","bg2.jpg","bg3.jpg","bg4.jpg"];
    const color = ref();
</script>