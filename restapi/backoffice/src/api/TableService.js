

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

        
        var data={
            "title":state.tableName,
            "enabled": true,
            "tableCount":state.tableCount
        }

        //localhost:8080/table/add/2
        let response=fetch(`http://localhost:8080/table/add`,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;

    }

    deleteTable(id){

        var myHeaders=new Headers;
        myHeaders.append("Authorization", this.token)

        //http://localhost:8080/table/delete/1
        let response=fetch(`http://localhost:8080/table/delete/${id}`,{

            method:'DELETE',
            headers:myHeaders,

        })

        return response;
    }

    updateTable(id,item){

        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");


      /*   "id": 2,
        "title": "Salon",
        "enabled": true,
        "tableCount": 20
     */
        
        var data={
            "title":item.tableName,
            "enabled":true,
            "tableCount":item.tableCount
        }
        console.log(data,id)
      //localhost:8080/table/update/1
        let response=fetch(`http://localhost:8080/table/update/${id}`,{

            method: 'PUT',
            headers:myHeaders,
            body: JSON.stringify(data)
        })


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