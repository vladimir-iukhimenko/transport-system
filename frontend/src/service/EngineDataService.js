import axios from "axios";

const ENGINE_API_URL = "http://localhost:8081/api";

class EngineDataService{
    getAllEngines() {
        return axios.get(`${ENGINE_API_URL}/engines`);
    }
    deleteEngine(id) {
        return axios.delete(`${ENGINE_API_URL}/engines/delete/${id}`)
    }
    retrieveEngine(id) {
        return axios.get(`${ENGINE_API_URL}/engines/${id}`)
    }
    updateEngine(id, engine) {
        return axios.put(`${ENGINE_API_URL}/engines/update/${id}`, engine)
    }
    createEngine(engine) {
        return axios.post(`${ENGINE_API_URL}/engines/create`,engine)
    }
}

export default new EngineDataService();