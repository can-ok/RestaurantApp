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

        const {userName,userPass,selectedRole}=item;

        console.log(selectedRole.value)
        var userCandidate={
            "username":userName,
            "password":userPass,
            "enabled":"true",
            "roles":selectedRole
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

    updateUser(state){

        var headers={"Authorization":this.token,"Content-Type":"application/json"}

        let response;

        let id=state.id
        let userData={
            "id":state.id,
            "username":state.userName,
            "password":state.userPass,
            "authority":state.userRole,
            "enabled":true,
            "roles":state.selectedRole
        }


        response=axios.put(`http://localhost:8080/users/update/${id}`,userData,{
            headers:headers
        })

        return response;
    }


    getUserById(id){
        
        var headers={"Authorization":this.token,"Content-Type":"application/json"}

        let response;

        response=axios.get("http://localhost:8080/users/get/"+id,{
            headers:headers
        })

        return response;
    }

    getAllRoles(){
        var headers={"Authorization":this.token}
        let response;

        //http://localhost:8080/roles
        response=axios.get("http://localhost:8080/roles",{
            headers:headers
        })

        return response;

    }

    saveRole(id,name){
        var headers={"Authorization":this.token,"Content-Type":"application/json"}
        let response;

        //http://localhost:8080/roles
        let userData={
            "id":id,
            "name":name
        }


        axios.post(`http://localhost:8080/roles`,userData,{
            headers:headers
        })

        return response;

    }

    updateRole(id,name){
        var headers={"Authorization":this.token,"Content-Type":"application/json"}
        let userData={
            "id":id,
            "name":name
        }
        //http://localhost:8080/roles/2
        let response=axios.put(`http://localhost:8080/roles/${id}`,userData,{
            headers:headers
        })

        return response;
    }




}


export default new UserSerivce();