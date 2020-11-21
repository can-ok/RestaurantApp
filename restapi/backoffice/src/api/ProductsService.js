

class ProductsService{


     getProduct(name){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", "BASIC dXNlcjE6cGFzczE=");


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
        myHeaders.append("Authorization", "BASIC dXNlcjE6cGFzczE=");


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
        myHeaders.append("Authorization", "BASIC dXNlcjE6cGFzczE=");

        var data={
            "title":item.itemTitle,
            "description":item.itemDescription,
            "price":item.price,
            "productCategory":item.productCategory
            };


        let response=fetch("http://localhost:8080/products/add/"+type,{

        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify(data)
        })

        return response;
    }
     


}


export default new ProductsService();