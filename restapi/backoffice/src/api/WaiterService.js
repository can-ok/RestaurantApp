import axios from 'axios';
import context from 'react-bootstrap/esm/AccordionContext';

class WaiterService{
    
    static token=" "

    getAllWaiters(){

        var headers={'Authorization':this.token}


        //http://localhost:8080/waiters/getAll
        let response=axios.get('http://localhost:8080/waiters/getAll',{

        headers:headers
        })

        return response;
    }


    addWaiter(state){

        var myHeaders=new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");

        
        var data={
            "firstname":state.waiterFirstName,
            "lastname":state.waiterLastName,
            "email":state.waiterEmail,
            "birtdate":state.selectedDate,
            "media":state.selectedMedia.value
        }

        //localhost:8080/table/add/2
        let response=fetch(`http://localhost:8080/waiters/save`,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;


    }

    deleteWaiter(id){
    
        var myHeaders=new Headers();
        myHeaders.append("Authorization", this.token);
        
        //http://localhost:8080/waiters/delete/1

        let response=fetch(`http://localhost:8080/waiters/delete/${id}`,{

            method:'DELETE',
            headers:myHeaders,

        })

        return response;

    }

    updateWaiter(id,state){

        var myHeaders=new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");

        var data={
            "id":id,
            "firstname":state.waiterFirstName,
            "lastname":state.waiterLastName,
            "email":state.waiterEmail,
            "birtdate":state.selectedDate,
            "media":state.selectedMedia.value
        }

        let response=fetch(`http://localhost:8080/waiters/update/${id}`,{

            method: 'PUT',
            headers:myHeaders,
            body: JSON.stringify(data)
        })


        return response;

    }




}

export default new WaiterService();