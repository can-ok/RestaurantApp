

import axios from 'axios';

class AuthService{


    
    login(item){

        var candidate={
            "userName":item.username,
            "password":item.password,
        }

        let response=axios.post("http://localhost:8080/register",candidate)

        return response;

    }

}

export default new AuthService()