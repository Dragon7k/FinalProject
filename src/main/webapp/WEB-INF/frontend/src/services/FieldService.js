import axios from "axios";

const GET_FIELD_URL = "http://localhost:8080/mainServlet?command=HOME_PAGE";
class FieldService{

    getField(){
        return axios.get(GET_FIELD_URL);
    }

    /*addField(field){
        return axios.post(BASE_URL, field)
    }

    deleteField(id){
        return axios.delete(BASE_URL+'/'+id)
    }

    updateField(id,field){
        return axios.put(BASE_URL+'/'+id, field);
    }*/
    
}

export default new FieldService();