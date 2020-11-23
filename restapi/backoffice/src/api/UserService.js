import axios from 'axios';


class UserSerivce{
    token=localStorage.getItem("token");

    getAllUser(){
        /* var myHeaders = new Headers();
        myHeaders.append("Authorization", "BASIC YWRtaW46YWRtaW4xMjM=");
 */
        var headers={"Authorization":this.token}
        let response


        response=axios.get("http://localhost:8080/users/getAll",{
        headers:headers
        })


        return response;
    }

   


    addUser(item){

        var headers={"Authorization":this.token,"Content-Type":"application/json"}

        const {userName,userPass,userRole}=item;
        var userCandidate={
            "userName":userName,
            "password":userPass,
            "authority":userRole,
            "enabled":"true"
        }
        //http://localhost:8080/users/save

        let response=axios.post("http://localhost:8080/users/save",userCandidate,{
        headers:headers
        })

        return response;
    }



}


export default new UserSerivce();