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

    deleteUser(itemId){

        var headers={"Authorization":this.token,"Content-Type":"application/json"}

        let response;
        response=axios.delete("http://localhost:8080/users/delete/"+itemId,{
        headers:headers

        })

        return response;
    }

    updateUser(id,userData){

        var headers={"Authorization":this.token,"Content-Type":"application/json"}

        let response;


        response=axios.put(`http://localhost:8080/users/update/${id}`,userData,{
            headers:headers
        })

        return response;
    }


    getUserById(id){
        
        var headers={"Authorization":this.token}

        let response;

        response=axios.get("http://localhost:8080/users/get/"+id,{
            headers:headers
        })

        return response;
    }


}


export default new UserSerivce();