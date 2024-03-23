import { ref, onMounted } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const nodes = ref([]);

    const fetchData = async () => {
      try {
        const response = await axios.get('http://localhost:8081/categorie');
        nodes.value = response.data; // Assuming the response contains an array
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    onMounted(() => {
      fetchData();
    });

    return {
      nodes
    };
  }
};
