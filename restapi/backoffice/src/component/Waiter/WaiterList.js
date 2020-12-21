import React, { Component } from 'react';
import WaiterService from '../../api/WaiterService';
import {GrFormAdd} from 'react-icons/gr'
import {Link} from 'react-router-dom';
import { Button } from 'react-bootstrap';
import PageLoader from '../PageLoader';

import AppContext from '../../AppContext';

class WaiterList extends Component {
    state = { waiters:[],loading:true  }

    static contextType=AppContext;


    componentDidMount(){
        
        let appContext=this.context;
        let token=appContext.appState.token?appContext.appState.token:localStorage.getItem('token')

        WaiterService.token=token;

        WaiterService.getAllWaiters().
        then((response)=>{

            this.setState({
                waiters:response.data,
                loading:false
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
                <td>{item.firstname}</td>
                <td>{item.lastname}</td>
                <td>{item.birtdate}</td>
                <td><img src={'data:image/png;base64,'+item.media.fileContent} width="60" alt="waiter"/></td>
                <td><Link to={`/editWaiter/${item.id}`} className="btn btn-warning">Düzenle</Link></td>
                <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Sil </Button></td>
                </tr>


            )
            
        }

        return ( 
            <div>
            <PageLoader loading={this.state.loading} />

            <div className="mb-3">
            <strong>Garson List</strong>
            <Link className="btn float-right" to="/addWaiter"><GrFormAdd size='1rem'/><strong>Ekle Garson</strong></Link>

            </div>
            <table className="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>İsim</th>
                    <th>Soyisim</th>
                    <th>Doğum Tarihi</th>
                    <th>Görsel</th>
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