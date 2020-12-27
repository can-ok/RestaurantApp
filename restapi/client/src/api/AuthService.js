import axios from 'axios';

class AuthService{
    
    static language=''
    
    login(item){

        var candidate={
            "username":item.username,
            "password":item.password,
        }

        let response=axios.post("http://localhost:8080/register",candidate,{headers:{'accept-language':this.language}})

        console.log(response)
        return response;

    }

}

export default new AuthService()