<template>
    <div></div>
</template>
  
<script>
import axios from 'axios'

export default {
    mounted() {
        axios
            .get('http://localhost:8080/testModel/api')
            .then((response) => {
                console.log(response.data)
            })
    }
}
</script>