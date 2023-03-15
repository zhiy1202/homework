import axios from 'axios'
const instance = axios.create({
    baseURL: 'http://localhost:8002',
    timeout: 2000
});
instance.defaults.withCredentials = true
export default instance