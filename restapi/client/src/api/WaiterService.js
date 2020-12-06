import axios from 'axios';

class WaiterService{

    token=localStorage.getItem('token')
    

    getAllWaiters(){

        var headers={'Authorization':this.token}


        //http://localhost:8080/waiters/getAll
        let response=axios.get('http://localhost:8080/waiters/getAll',{

        headers:headers
        })

        return response;
    }


}

export default new WaiterService();