import React, { Component } from 'react';
import WaiterService from '../../api/WaiterService';
import {GrFormAdd} from 'react-icons/gr'
import {Link} from 'react-router-dom';
import { Button } from 'react-bootstrap';

import OrderService from '../../api/OrderService';

class OrderList extends Component {
    state = { 
            orders:[]
     }


    componentDidMount(){


        OrderService.getAllOrders().then(
            (response)=>{
                this.setState({orders:response.data})
            }
        
        ).catch((err)=>{console.log(err)})


    }


    render() { 

        const {orders}=this.state;
        let ordersList;

        if(orders.length>0){
            
            ordersList=orders.map((item)=>{

       return( <tr key={item.id} >
           <td>{item.id}</td>
            <td>{item.orderDate}</td>
            <td>{item.orderTable}</td>
            <td>{item.productCount}</td>
            <td>{item.productId}</td>
            <td>{item.totalPrice}</td>
            <td>{item.waiterId}</td>
            <td><Link to={`/editTable/${item.id}`} className="btn btn-warning">Edit</Link></td>
            <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Delete </Button></td>
        </tr>)

            })
        }

        return (  
            <div>
            <div className="mb-3">
            <strong>Order List</strong>
            <Link className="btn float-right" to="/addTable"><GrFormAdd size='1rem'/><strong>Add Order</strong></Link>

            </div>
            <table className="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>Date</th>
                    <th>Table</th>
                    <th>Count</th>
                    <th>Product Id</th>
                    <th>Total</th>
                    <th>Waiter</th>
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                    {ordersList}
                </tbody>

                
            </table>

            </div>
        );
    }
}
 
export default OrderList;