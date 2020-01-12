import axios from "axios";

const ENGINE_API_URL = "http://localhost:8081/api";

class EngineDataService{
    getAllEngines() {
        return axios.get(`${ENGINE_API_URL}/engines`);
    }
    deleteEngine(id) {
        return axios.delete(`${ENGINE_API_URL}/engines/delete/${id}`)
    }
}

export default new EngineDataService();