<template>
   
   

</template>


<script setup>
    
    import InputGroup from 'primevue/inputgroup';
    import InputGroupAddon from 'primevue/inputgroupaddon';
    import FileUpload from 'primevue/fileupload';
    import { useToast } from "primevue/usetoast";
    const toast = useToast();

    const onAdvancedUpload = () => {
        toast.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000 });
    };

</script>