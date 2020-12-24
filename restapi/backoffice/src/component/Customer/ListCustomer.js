import React,{useEffect, useState,useContext} from 'react';
import AppContext from '../../AppContext';
import {Link} from 'react-router-dom';
import PageLoader from '../PageLoader';
import {GrFormAdd} from 'react-icons/gr'

import CustomerService from '../../api/CustomerService';


const CustomerList = () => {

    const context=useContext(AppContext);
    const [pageSize,setPageSize]=useState(5);
    const [pageCount,setPageCount]=useState(0);
    const [token,setToken]=useState(null)
    const [loading,setLoading]=useState(true)
    const [items,setItems]=useState([])
    const [pageNumbers,setPageNumbers]=useState([])

    useEffect(()=>{

        let token=context.appState.toke?context.appState.token:localStorage.getItem('token')
        CustomerService.token=token;
        
        CustomerService.getCustomers(pageSize,pageCount)
        .then((response)=>{

            let arr=new Array();
            for(let i = 1; i <= Math.ceil(response.data.totalElements/pageSize); i++){
                arr.push(i);
              }
            if(response.status==200){
                setItems(response.data.content)
                setLoading(false)
                setPageNumbers(arr)
            }
        })
        .catch((err)=>console.log(err))
    
    },[])



    let pagination=pageNumbers.map((number=>{
        return(
        <li key={number} onClick={()=>paginate(number)} className="page-item">
            <a href="#" className="page-link">{number}</a>
        </li>)
   }))


    let paginate=(number)=>{

        CustomerService.getCustomers(pageSize,number-1)
        .then((response)=>{
            let arr=new Array();
            for(let i = 1; i <= Math.ceil(response.data.totalElements/pageSize); i++){
                arr.push(i);
              }
            if(response.status==200){
                setItems(response.data.content)
                setPageNumbers(arr)
            }
            
        })
        .catch((error)=>{

            console.error("Error :",error)
        })

    }



    return ( <div>

        <PageLoader loading={loading} />
            <div className="mb-3">
            <strong>Drink List</strong> 
            <Link className="btn float-right" to="/customer/add"><GrFormAdd size='1rem'/><strong>Add Customer</strong></Link>

            </div>

            <table className="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>İsim</th>
                    <th>Açıklama</th>
                    <th>Görsel</th>
                    <th>Kategori</th>
                    <th>Fiyat</th>
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                {items.map((item)=>{
                    return(
                            <tr key={item.customerId}>
                            <td>{item.customerId}</td>
                            <td>{item.firstName}</td>
                            <td>{item.lastName} </td>
                            <td>{item.city} </td>
                            <td>{item.address} </td>
                            <td>{item.phoneNumber} </td>
                            <td><Link to={`/update/${"customer"}/${item.customerId}`}  className="btn btn-warning">Edit</Link></td>
                            
                            </tr>)
                 })}
                </tbody>  
                </table>
                <ul className='pagination d-flex justify-content-center'>

                {pagination}
                </ul>

    </div>  );
}
 
export default CustomerList;