import axios from "axios";

axios.defaults.baseURL = "localhost:8081/";
axios.defaults.headers.common['Authorization']= 'Bearer ' + localStorage.getItem('token');