import React, { Component } from 'react';
import TableService from '../../api/TableService';
import {GrFormAdd} from 'react-icons/gr'
import {Link} from 'react-router-dom';
import { Button } from 'react-bootstrap';


class TableList extends Component {
    state = { tables:[] }

    
    componentDidMount(){

        TableService.getTables().then((response)=>{

            return response.json()
        }).then((data)=>{

            this.setState({
                tables:data
            })
        })
    }

    handle_detele=(id)=>{
        console.log(id)
        const tables=this.state.tables.filter(item=> item.id !== id)
        this.setState({tables})

        TableService.deleteTable(id).then((response)=>console.log(response))
        .catch((err)=>console.error(err))
    }


    render() {
        
        
        const {tables}=this.state;
        //const type="drink";
        let listTable=null;
        if(tables.length>0){
        listTable= tables.map((item)=> 
        
        <tr key={item.id} >
            <td>{item.id}</td>
            <td>{item.title}</td>
            <td>{item.tableCount}</td>
            <td>{item.enabled.toString()}</td>
            <td><Link to={`/editTable/${item.id}`} className="btn btn-warning">Edit</Link></td>
            <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Delete </Button></td>
        </tr>)
        }
        
        return ( <div>

            <div className="mb-3">
            <strong>Table List</strong>
            <Link className="btn float-right" to="/addTable"><GrFormAdd size='1rem'/><strong>Add Table</strong></Link>

            </div>
            <table className="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>İsim</th>
                    <th>Sayı</th>
                    <th>Enable</th>
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                {listTable}
                </tbody>
            </table>
            
            
        </div> );
    }
}
 
export default TableList;