import axios from "axios";

const TRANSPORT_API_URL = "http://localhost:8081/api";

class RestAPIService{
    readAll(path) {
        return axios.get(`${TRANSPORT_API_URL}/${path}`)
    }
    retrieve(id,path) {
        return axios.get(`${TRANSPORT_API_URL}/${path}/${id}`)
    }
    create(path,object) {
        return axios.post(`${TRANSPORT_API_URL}/${path}/add`, object)
    }
    update(path,object) {
        return axios.put(`${TRANSPORT_API_URL}/${path}/edit`, object)
    }
    delete(id,path) {
        return axios.delete(`${TRANSPORT_API_URL}/${path}/delete/${id}`)
    }
}

export default new RestAPIService();