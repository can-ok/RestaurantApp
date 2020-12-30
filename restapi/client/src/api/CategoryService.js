

class CategoryService{


    static token=""

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
}

export default new CategoryService();