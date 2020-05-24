import axios from "axios"

class AuthService {
    login(user){
        return axios.post(`${process.env.VUE_APP_REST_API_URL}/auth/signin`,{
            username: user.username,
            password: user.password
        })
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return axios.post(`${process.env.VUE_APP_REST_API_URL}/auth/signup`,{
            username: user.username,
            password: user.password
        });
    }
}

export default new AuthService();