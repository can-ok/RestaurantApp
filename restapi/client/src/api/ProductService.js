import axios from 'axios';

class ProductService{

    static token=""

   getCategories(){

        var headers={"Authorization":this.token}
        let response

        response=axios.get("http://localhost:8080/products/getCategories",{
        headers:headers
        })
        
        return response;
   } 


   getAllProducts(pageSize,pageCount){
        var headers={"Authorization":this.token}
        let response

        //http://localhost:8080/products/getAll/?page=20&size=5

        response=axios.get(`http://localhost:8080/products/getAll/?page=${pageCount}&size=${pageSize}`,{
            headers:headers
            })
            
    return response;

   }

   getProductsByCategory(category,pageSize,pageCount){



    var headers={"Authorization":this.token}
    let response

    http://localhost:8080/products/category/1/?page=0&size=5
    response=axios.get(`http://localhost:8080/products/category/${category}/?page=${pageCount}&size=${pageSize}`,{
        headers:headers
    })
            
        return response;
   }

   
}


export default new ProductService();