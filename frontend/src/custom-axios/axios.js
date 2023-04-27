import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:9091/api',
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Acces-Control-Allow-Methods' : 'GET, PUT, POST, DELETE, PATCH, OPTIONS'/*,
        'Authorization': localStorage.getItem("JWT")*/
    },
    withCredentials: false
})

export default instance;
