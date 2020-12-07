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


    
    handle_detele=(id)=>{
        
        const waiters=this.state.waiters.filter(item=> item.id !== id)
        this.setState({waiters})

        WaiterService.deleteWaiter(id).then((response)=>console.log(response))
        .catch((err)=>console.error(err))
    }

    render() { 
        const {waiters}=this.state;

        let itemList;
        if(waiters.length>0){
            itemList=waiters.map((item)=>
                <tr key={item.id} >
                <td>{item.id}</td>
                <td>{item.name}</td>
                <td><Link to={`/editWaiter/${item.id}`} className="btn btn-warning">Düzenle</Link></td>
                <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Sil </Button></td>
                </tr>


            )
            
        }

        return ( 
            <div>
            <div className="mb-3">
            <strong>Garson List</strong>
            <Link className="btn float-right" to="/addWaiter"><GrFormAdd size='1rem'/><strong>Ekle Garson</strong></Link>

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