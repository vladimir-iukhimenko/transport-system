import axios from "axios";
import authHeader from "./AuthHeader";

const TRANSPORT_API_URL = "http://localhost:8081/api";

class RestAPIService{
    readAll(path) {
        return axios.get(`${TRANSPORT_API_URL}/${path}`, { headers: authHeader() });
    }
    retrieve(id,path) {
        return axios.get(`${TRANSPORT_API_URL}/${path}/${id}`, {headers: authHeader()});
    }
    create(path,object) {
        return axios.post(`${TRANSPORT_API_URL}/${path}/add`, object, {headers: authHeader()});
    }
    update(path,object) {
        return axios.put(`${TRANSPORT_API_URL}/${path}/edit`, object, {headers: authHeader()});
    }
    delete(id,path) {
        return axios.delete(`${TRANSPORT_API_URL}/${path}/delete/${id}`, {headers: authHeader()});
    }
}

export default new RestAPIService();