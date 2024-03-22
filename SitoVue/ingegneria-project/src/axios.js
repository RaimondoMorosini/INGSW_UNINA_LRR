import axios from "axios";

axios.defaults.baseURL = "localhost:8081/";
axios.defaults.headers.common['Autorization']= 'Bearer ' + localStorage.getItem('token');