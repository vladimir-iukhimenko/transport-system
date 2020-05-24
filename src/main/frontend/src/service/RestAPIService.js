import axios from "axios";
import authHeader from "./AuthHeader";

class RestAPIService{
    readAll(path) {
        return axios.get(`${process.env.VUE_APP_REST_API_URL}/${path}`, { headers: authHeader() });
    }
    retrieve(id,path) {
        return axios.get(`${process.env.VUE_APP_REST_API_URL}/${path}/${id}`, {headers: authHeader()});
    }
    create(path,object) {
        return axios.post(`${process.env.VUE_APP_REST_API_URL}/${path}/add`, object, {headers: authHeader()});
    }
    update(path,object) {
        return axios.put(`${process.env.VUE_APP_REST_API_URL}/${path}/edit`, object, {headers: authHeader()});
    }
    delete(id,path) {
        return axios.delete(`${process.env.VUE_APP_REST_API_URL}/${path}/delete/${id}`, {headers: authHeader()});
    }
    search(object) {
        return axios.post(`${process.env.VUE_APP_REST_API_URL}/search`, object, { headers: authHeader() });
    }
}

export default new RestAPIService();