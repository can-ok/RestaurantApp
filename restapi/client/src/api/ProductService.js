import axios from 'axios';

class ProductService{

    token=localStorage.getItem("token")

   getCategories(){

        var headers={"Authorization":this.token}
        let response

        response=axios.get("http://localhost:8080/products/getCategories",{
        headers:headers
        })
        
        return response;
   } 


   getAllProducts(){
        var headers={"Authorization":this.token}
        let response


        response=axios.get("http://localhost:8080/products/getAll",{
            headers:headers
            })
            
    return response;

   }

   getProductsByCategory(category){



    var headers={"Authorization":this.token}
    let response


    response=axios.get(`http://localhost:8080/products/category/${category}`,{
        headers:headers
    })
            
        return response;
   }

   
}


export default new ProductService();