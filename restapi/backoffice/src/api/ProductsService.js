

class ProductsService{

    static token=""

     getProduct(pageSize,pageCount){
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);


        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };

        let response
        
        //http://localhost:8080/products/?page=0&size=5
        response=fetch(`http://localhost:8080/products/?page=${pageCount}&size=${pageSize}`,requestOptions)
        

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

        const productCategory=item.selectValue.map((itemObject)=>{
            var category=itemObject.split(',')
            
            var rObj={"id":category[0],"name":category[1]};
            return rObj
        });


        var data={
            "title":item.itemTitle,
            "description":item.itemDescription,
            "price":item.price,
            "productcategory":productCategory,
            "media":item.selectedMedia.value
            };



        
        let response=fetch(`http://localhost:8080/products/add/${type}`,{

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

    updateProduct(type,item){

        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
        myHeaders.append("Content-Type", "application/json");

        console.log(item)
        console.log(item.selectedCategories)



        var data={
            "id":item.id,
            "title":item.itemTitle,
            "description":item.itemDescription,
            "price":item.price,
            "productcategory":item.selectedCategories,
            "media":item.selectedMedia.value

            };
        
              //http://localhost:8080/products/update/drink/1
        let response=fetch(`http://localhost:8080/products/update/${type}/`,{

            method: 'PUT',
            headers:myHeaders,
            body: JSON.stringify(data)
        })


        return response; 
    }


    
     


}


export default new ProductsService();