

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

     addTable(state){
    
        var myHeaders=new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");

        let id=state.selectValue;
        var data={
            "title":state.tableName,
            "enabled": true

        }

        //localhost:8080/table/add/2
        let response=fetch(`http://localhost:8080/table/add/${id}`,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;

    }
 

}

export default new TableService();