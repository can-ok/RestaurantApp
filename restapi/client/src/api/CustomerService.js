import axios from 'axios';

class CustomerService{

    static token=""

    getCustomers(pageSize,pageCount){
        var headers={"Authorization":this.token}
        let response

        //http://localhost:8080/http://localhost:8080/customers/?page=20&size=5

        response=axios.get(`http://localhost:8080/customers/?page=${pageCount}&size=${pageSize}`,{
            headers:headers
        })
            
        return response;
    }

    addCustomer(item){

        var myHeaders = new Headers();
        myHeaders.append("Authorization",this.token);
        myHeaders.append("Content-Type", "application/json");
        
        var data={
            "firstName":item.firstName,
            "lastName":item.lastName,
            "phoneNumber":item.phoneNumber,
            "city":item.selectedCity,
            "address":item.address
            };


        console.log(data)
        
        let response=fetch(`http://localhost:8080/customers`,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;
       
    }

    findCustomer(phoneNumber,pageSize,pageCount){

        var myHeaders = new Headers();
        myHeaders.append("Authorization",this.token);
        myHeaders.append("Content-Type", "application/json");
        
        var data={

            "phoneNumber":phoneNumber,
    
            };
            

        console.log(data)
        

        let response=fetch(`http://localhost:8080/customers/byNumber?page=${pageCount}&size=${pageSize}`,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;
       
    }




}

export default new CustomerService();