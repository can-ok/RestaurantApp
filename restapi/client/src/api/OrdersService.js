

class OrdersService{

    static token=""

    saveOrders(data){

        var myHeaders = new Headers();
        myHeaders.append("Authorization",this.token);
        myHeaders.append("Content-Type", "application/json");

        //http://localhost:8080/orders/saveOrder
        let response=fetch("http://localhost:8080/orders/saveOrder",{

            method:'POST',
            headers:myHeaders,
            body: JSON.stringify(data)
        })

        return response;    
    }


}


export default new OrdersService();