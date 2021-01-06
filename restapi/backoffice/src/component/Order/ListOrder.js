import React, { useEffect, useState, useContext } from "react";
import AppContext from "../../AppContext";
import { Link } from "react-router-dom";
import PageLoader from "../PageLoader";
import { Button } from 'react-bootstrap';
import { GrFormAdd } from "react-icons/gr";

import OrderService from '../../api/OrderService';




const ListOrder = () => {
    const context = useContext(AppContext);
    const [pageSize, setPageSize] = useState(5);
    const [pageCount, setPageCount] = useState(0);
    const [token, setToken] = useState(null);
    const [loading, setLoading] = useState(true);
    const [items, setItems] = useState([]);

    const [showListCompoent,setListComponent]=useState(true)
    const [showAddCompoent,setAddComponent]=useState(false)
    const [showEditCompoent,setEditComponent]=useState(false)
  

    useEffect(() => {
        let token = context.appState.toke
          ? context.appState.token
          : localStorage.getItem("token");
          OrderService.token = token;

          OrderService.getAllOrders()
          .then((response)=>{
                  
                if(response.status==200){
                    console.log(response.data)
                    setItems(response.data);
                    setLoading(false);
                }
               
              }
          
          ).catch((err)=>{console.log(err)})
        
      }, []);


    let table=(items,showEditCustomer,handle_detele)=>{

        if(items.length<1){
          return null;
        }
    
        return(<table className="table">
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
          {items.map((item) => {
            return( <tr key={item.id} >
            <td>{item.id}</td>
                <td>{item.orderDate}</td>
                <td>{item.orderTable}</td>
                <td>{item.productCount}</td>
                <td>{item.productId}</td>
                <td>{item.totalPrice}</td>
                <td>{item.waiterId?item.waiterId.firstname:"BOŞ"}</td>
                <td><Link to={`/editTable/${item.id}`} className="btn btn-warning">Edit</Link></td>
                <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Delete </Button></td>
            </tr>)
          })}
        </tbody>
        </table>)
       }

   

    return ( <div>

            {showListCompoent && 
                <div>
                <PageLoader loading={loading} />
                <div className="mb-3">
                    <strong>Customer List</strong>

                    <div className="float-right">
                   
                    <a className="btn " onClick={()=>{setAddComponent(true); setListComponent(false); }}>
                    <GrFormAdd size="1rem" />
                    <strong>Add Customer</strong>
                    </a>
                    </div>
                </div>

                    {table(items,null,null)}
                    
                </div> }    
            </div>
);
}
 
export default ListOrder;