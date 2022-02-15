import axios from "axios";

const BASE_URL = "http://localhost:8080/mainServlet?command=SIGN_UP";
const BASE_URL1 = "http://localhost:8080/login/edit_profile";
const BASE_URL2 = "http://localhost:8080/login/change_pass";
//const BASE_URL1 = "http://localhost:8080/signUp";
class UserService{

    getUserByEmail(email){
        return axios.get(BASE_URL+'/'+email);
    }

    getUserOldPass(id){
        return axios.get(BASE_URL2+'/'+id)
    }

    updateUserPass(id, user){
        return axios.put(BASE_URL2+'/'+id, user)
    }
    updateUser(id, user){
        return axios.put(BASE_URL1+'/'+id, user)
    }

    addUser(user){
        return axios.post(BASE_URL,user);
    }
}

export default new UserService();