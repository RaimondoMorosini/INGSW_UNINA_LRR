<template>
    <div>
      <input type="file" @change="handleFileUpload">
      <button @click="uploadImage">Carica immagine</button>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        selectedFile: null
      };
    },
    methods: {
      handleFileUpload(event) {
        this.selectedFile = event.target.files[0];
      },
      uploadImage() {
        if (!this.selectedFile) {
          alert("Seleziona un'immagine prima di caricare");
          return;
        }
  
        const formData = new FormData();
        formData.append('file', this.selectedFile);
  
        fetch('http://localhost:8081/public/uploadImage', {
          method: 'POST',
          body: formData
        })
        .then(response => {
          if (!response.ok) {
            throw new Error('Errore durante il caricamento dell\'immagine');
          }
          return response.text();
        })
        .then(data => {
          console.log(data); // Il messaggio di risposta dal server
          alert('Immagine caricata con successo!');
        })
        .catch(error => {
          console.error('Errore:', error);
          alert('Errore durante il caricamento dell\'immagine');
        });
      }
    }
  };
  </script>
  