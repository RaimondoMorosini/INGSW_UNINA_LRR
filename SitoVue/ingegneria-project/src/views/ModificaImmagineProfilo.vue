<template>
    <div class="mb-6">
      <label class="block text-sm font-medium text-gray-700 mb-2">Immagine Profilo</label>
      <div class="relative inline-block">
        <img :src="immagineAnteprima || immagineOriginale" alt="Anteprima" class="w-32 h-32 rounded-full object-cover border-2 border-gray-300 shadow-md" />
        <label class="absolute bottom-0 right-4 translate-x-1/2 bg-primario-500 text-white rounded-full p-2 flex items-center justify-center hover:bg-primario-600 transition duration-200 transform hover:scale-105" style="width: 40px; height: 40px;">
          <input type="file" class="hidden" accept="image/*" @change="aggiornaImmagine" />
          <i class="pi pi-camera text-xl"></i>
        </label>
      </div>
      <p v-if="fileError.code !== 0" class="text-red-500 text-sm mt-2 flex items-center">
        <i class="pi pi-exclamation-circle w-4 h-4 mr-1"></i>
        {{ fileError.message }}
      </p>
    </div>
  </template>
  
  <script setup>
  import 'primeicons/primeicons.css';
  import { ref, defineEmits } from 'vue';
  
  const emit = defineEmits(['immagineModificata']);
  
  const props = defineProps({
    immagineOriginale: String
  });
  
  const immagineAnteprima = ref(null);
  const fileError = ref({
    code: 0,
    message: 'Nessun errore',
  });
  
  const allowedFormats = ['image/jpeg', 'image/jpg', 'image/png', 'image/gif'];
  const maxFileSize = 5 * 1024 * 1024;
  
  const validateFile = (file) => {
    try {
      fileError.value = { code: 0, message: '' };
  
      if (!file) {
        fileError.value = { code: 2, message: 'Nessun file selezionato.' };
        return false;
      }
      if (!allowedFormats.includes(file.type)) {
        fileError.value = { code: 3, message: 'Formato file non valido. Sono consentiti solo JPEG, JPG, PNG e GIF.' };
        return false;
      }
      if (file.size > maxFileSize) {
        fileError.value = { code: 4, message: 'Il file supera il limite di dimensione di 5 MB.' };
        return false;
      }
    } catch (e) {
      console.log('Errore durante la validazione del file:', e);
      fileError.value = { code: 1, message: 'Errore anomalo durante la validazione del file. Riprova con un altro file.' };
      return false;
    }
  
    return true;
  };
  
  const aggiornaImmagine = (evento) => {
    const file = evento.target.files[0];
    if (file && validateFile(file)) {
      immagineAnteprima.value = URL.createObjectURL(file);
      
      // Convert image to base64 URL (for storage in a database)
      const reader = new FileReader();
      reader.onload = () => {
          immagineAnteprima.value = reader.result; // Use reader.result to save to the database
          console.info('Immagine convertita in base64:', immagineAnteprima.value);
          emit('immagineModificata', immagineAnteprima.value);

        };
        reader.readAsDataURL(file);
        
    }
  };
  </script>
  