import React, { Component } from 'react';
import TableService from '../../api/TableService';
import {Link} from 'react-router-dom'
import {ListGroup, ListGroupItem} from 'reactstrap';

import './Table.css'

class TableList extends Component {
    state = { tables:[]  }




    componentDidMount(){

        TableService.getTables().then((response)=>{

            return response.json()
        }).then((data)=>{

            this.setState({
                tables:data
            })
        })
    }

    selectTable=(table)=>{

        localStorage.setItem("table",table)

        window.location="/"

    }

    render() { 

         const tablesList=this.state.tables.map((item)=>

         <tr key={item.id} className="table-row" onClick={()=>this.selectTable(item.title)}>
            <td>{item.title}</td>
            <td>{item.tableCategory.title}</td> 

         </tr>
        ) 

        return ( <div className="mt-5">
              <table className="table table-bordered table-condensed table-striped table-hover">
                <thead>
                <tr><th scope="col">Masa</th><th scope="col">Mekan</th></tr>
                </thead>
                <tbody>
                <tr className="table-row" onClick={()=>this.selectTable("Ayakta")}>
                    <td>Ayakta</td>
                    <td>Ayakta</td> 

                </tr>
                    {tablesList}
                </tbody>
              </table>

        </div>  );
    }
}
 
export default TableList;