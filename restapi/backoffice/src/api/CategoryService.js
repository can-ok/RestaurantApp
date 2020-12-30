

class CategoryService{


    static token=" "

    getCategories(){
       var myHeaders = new Headers();
       myHeaders.append("Authorization", this.token);


       var requestOptions = {
       method: 'GET',
       headers: myHeaders,
       };

       let response
       
       response=fetch(`http://localhost:8080/category/getAll`,requestOptions)
       

       return response;
    }

    getCategoryById(){
        //doldur
    }


    addCategories(state){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");

        var data={
            "name": state.itemTitle,
            "description":state.itemDescription,
            "categorymedia":state.selectedMedia.value

            };

    

        //localhost:8080/category/save
        let response=fetch(`http://localhost:8080/category/save`,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;

    }

    updateCategory(state){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");

        var data={
            "id":state.id,
            "name": state.itemTitle,
            "description":state.itemDescription,
            "categorymedia":state.selectedMedia.value
            };


        console.log(data)
        //localhost:8080/category/update/1
        let response=fetch(`http://localhost:8080/category/update/${state.id}`,{

            method: 'PUT',
            headers: myHeaders,
            body: JSON.stringify(data)
            })
    
        return response;

    }

    deleteCategory(id){

        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);


        var requestOptions = {
        method: 'DELETE',
        headers: myHeaders,
        };

        let response
        
        response=fetch(`http://localhost:8080/category/delete/${id}`,requestOptions)
        

        return response;
    }


    getTableCategories(){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
 
 
        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };
 
        let response

        //http://localhost:8080/tablecategory/getAll
        
        response=fetch(`http://localhost:8080/table/getAll`,requestOptions)
        
 
        return response;
     }

     
     getTablebyId(id){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
 
 
        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };
        //localhost:8080/table/update/1
        let response=fetch(`http://localhost:8080/table/getTable/${id}`,requestOptions)

        return response;

    }


}

export default new CategoryService();