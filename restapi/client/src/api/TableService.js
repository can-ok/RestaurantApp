

class TableService{
    static token=""


    getTables(){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
 
 
        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };
 
        let response
        
        response=fetch(`http://localhost:8080/table/getAll`,requestOptions)
        
 
        return response;
     }


     
    getReserved(){


        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
 
 
        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };
        

        let response=fetch('http://localhost:8080/table/getResvervedTable',requestOptions)

        return response;

    }
 

}

export default new TableService();