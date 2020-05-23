import axios from "axios";
import authHeader from "./AuthHeader";

const API_URL = 'https://trans-system.herokuapp.com/api'

class RestAPIService{
    readAll(path) {
        return axios.get(`${API_URL}/${path}`, { headers: authHeader() });
    }
    retrieve(id,path) {
        return axios.get(`${API_URL}/${path}/${id}`, {headers: authHeader()});
    }
    create(path,object) {
        return axios.post(`${API_URL}/${path}/add`, object, {headers: authHeader()});
    }
    update(path,object) {
        return axios.put(`${API_URL}/${path}/edit`, object, {headers: authHeader()});
    }
    delete(id,path) {
        return axios.delete(`${API_URL}/${path}/delete/${id}`, {headers: authHeader()});
    }
    search(object) {
        return axios.post(`${API_URL}/search`, object, { headers: authHeader() });
    }
}

export default new RestAPIService();