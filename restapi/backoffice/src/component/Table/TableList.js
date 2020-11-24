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


    render() {
        
        
        const {tables}=this.state;
        //const type="drink";
        let listTable=null;
        if(tables.length>0){
        listTable= tables.map((item)=> 
        
        <tr key={item.id} >
            <td>{item.id}</td>
            <td>{item.title}</td>
            <td>{item.tableCategory.title}</td>
            <td>{item.enabled.toString()}</td>
            <td><Link to={`/update/${"food"}/${item.id}`} className="btn btn-warning">Düzenle</Link></td>
            <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Sil </Button></td>
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
                    <th>Kategori</th>
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