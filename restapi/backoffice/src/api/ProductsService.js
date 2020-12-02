

class ProductsService{

    token=localStorage.getItem("token");

     getProduct(name){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);


        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };

        let response
        
        response=fetch(`http://localhost:8080/products/${name}`,requestOptions)
        

        return response;
     }

     deleteProduct(name,itemId){

        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);


        var requestOptions = {
        method: 'DELETE',
        headers: myHeaders,
        };

        let response
        
        response=fetch(`http://localhost:8080/products/delete/${name}/${itemId}`,requestOptions)
        

        return response;

     }

    addProduct(item,type){

        var myHeaders = new Headers();
        myHeaders.append("Authorization",this.token);
        myHeaders.append("Content-Type", "application/json");

        var data={
            "title":item.itemTitle,
            "description":item.itemDescription,
            "price":item.price,
            "productCategory":item.productCategory
            };

        let categoryId=item.selectValue;
        
        //http://localhost:8080/products/add/food/1

        
        let response=fetch(`http://localhost:8080/products/add/${type}/${categoryId}`,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;
    }


    getProductbyId(id,type){

        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);


        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };

        let response
        
        response=fetch(`http://localhost:8080/products/${type}/${id}`,requestOptions)
        

        return response;


    }

    updateProduct(id,type,item){

        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");


        var data={
            "title":item.itemTitle,
            "description":item.itemDescription,
            "price":item.price,
            "productCategory":item.productCategory
            };
        
               //http://localhost:8080/products/update/drink/1
        let response=fetch(`http://localhost:8080/products/update/${type}/${id}`,{

            method: 'PUT',
            headers:myHeaders,
            body: JSON.stringify(data)
        })


        return response;
    }


    
     


}


export default new ProductsService();