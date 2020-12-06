import React, { Component } from 'react';
import WaiterService from '../../api/WaiterService';
import {GrFormAdd} from 'react-icons/gr'
import {Link} from 'react-router-dom';
import { Button } from 'react-bootstrap';

class WaiterList extends Component {
    state = { waiters:[]  }

    componentDidMount(){
        
        WaiterService.getAllWaiters().
        then((response)=>{

            this.setState({
                waiters:response.data
            })

        })

    }
    render() { 
        const {waiters}=this.state;

        let itemList;
        if(waiters.length>0){
            itemList=waiters.map((item)=>
                <tr key={item.id} >
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td><Link to={`/editTable/${item.id}`} className="btn btn-warning">Düzenle</Link></td>
                <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Sil </Button></td>
                </tr>


            )
            
        }

        return ( 
            <div>
            <div className="mb-3">
            <strong>Table List</strong>
            <Link className="btn float-right" to="/addTable"><GrFormAdd size='1rem'/><strong>Add Table</strong></Link>

            </div>
            <table className="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>İsim</th>
                    
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                {itemList}
                </tbody>
            </table>

            </div>
         );
    }
}
 
export default WaiterList;