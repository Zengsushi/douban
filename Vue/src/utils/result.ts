import axios from "axios";

// import {ELMessage } from "element-plus";

const instance = axios.create({
    baseURL: "/api" ,
    timeout: 5000
})

export default instance


