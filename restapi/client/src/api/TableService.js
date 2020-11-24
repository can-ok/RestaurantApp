

class TableService{
    token=localStorage.getItem("token");


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
 

}

export default new TableService();