import axios from 'axios';


class OrderService{

    static token=""

    getAllOrders(){

        var headers={"Authorization":this.token}
        let response

        //http://localhost:8080/orders/getOrders
        response=axios.get("http://localhost:8080/orders/getOrders",{
        headers:headers
        })


        return response;
    }

}

export default new OrderService();