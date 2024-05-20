<template>
    <div>
        <label for="file-upload" class="custom-file-upload"> Custom Upload </label>
        <input id="file-upload" type="file" accept="image/*" multiple @change="handleChange" />
        <div class="grid grid-cols-4 justify-between gap-2">
            <div v-for="image in store.asta.immaginiSalvate" class="px-5 py-5">
                <img :src="image" alt="immagine caricata" class="h-28 shadow lg:h-32" />
            </div>

            <div class="areaBottoni flex flex-row gap-2 py-5">
                <label @click="eliminaUltima" class="h-24 w-24 rounded bg-primario-100 py-1"
                    ><i class="bi bi-file-minus icon-60"></i
                ></label>
                <label @click="eliminaTutto" class="h-24 w-24 rounded bg-primario-100 py-1"
                    ><i class="bi bi-trash3 icon-60"></i
                ></label>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useAstaStore } from '../../stores/astaStore';
const store = useAstaStore();

const eliminaUltima = () => {
    store.asta.immaginiSalvate.pop();
};

const eliminaTutto = () => {
    store.asta.immaginiSalvate = [];
};

const handleChange = (event) => {
    let newImgs = [];
    let newArray = [];
    for (let image of event.target.files) {
        newImgs.push(URL.createObjectURL(image));
    }
    console.log(newImgs);
    newArray = store.asta.immaginiSalvate.concat(newImgs);
    store.asta.immaginiSalvate = newArray;
    console.log('in store', store.asta.immaginiSalvate);
};
</script>

<style scoped>
input[type='file'] {
    display: none;
}

.custom-file-upload {
    background-color: #cc85f5;
    padding: 10px 20px;
    color: white;
    border-radius: 5px;
    font-size: 1.1rem;
    font-weight: bold;
    width: 50%;
    margin: 10px;
    cursor: pointer;
}
.custom-file-upload:hover {
    background-color: #7c3aed;
}
.icon-100 {
    font-size: 100px;
}
.icon-200 {
    font-size: 200px;
}
.icon-300 {
    font-size: 300px;
}
.icon-60 {
    font-size: 60px;
}
</style>
