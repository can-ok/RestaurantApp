import React, { Component } from 'react';
import TableService from '../../api/TableService';
import {Link} from 'react-router-dom'

import './Table.css'

class TableList extends Component {
    state = { tablesCategory:[],
              tables:[]
                }




    componentDidMount(){

        TableService.getTables().then((response)=>{

            return response.json()
        }).then((data)=>{

            this.setState({
                tablesCategory:data
            })
        })
    }

    showCards=(table)=>{

       if(table.title=='Ayakta'){
        this.selectTable(table.title)

       }
       else{

        let tables=[]

        //console.log(table)

        for(let count=1; count<=table.tableCount; count++){
            
            tables.push(`Masa ${count}`)
            
        }
        

        this.setState({

            tables
        })
        }
    }


    selectTable=(tableName)=>{
        localStorage.setItem("table",tableName)

        window.location="/" 
    }




    render() { 

         const tablesList=this.state.tablesCategory.map((item)=>

         <tr key={item.id} className="table-row" onClick={()=>this.showCards(item)}>
            <td>{item.title}</td>
         </tr>
        ) 


        const tablesCard=this.state.tables;

        let cards;

        if(tablesCard){

            
            cards=tablesCard.map((item)=>{
                

              return(<div className="col-md-4 mr-2 ml-2 mt-2 border" onClick={()=>this.selectTable(item)} >

                  
          
              <h5 className="row justify-content-center">
              {item}
              </h5>
              </div>)

            })
        

        }

        console.log(cards)

        return (<div>
            
        <div className="row mt-2 ">
 
            <div className="col float-left ">

            <table className="table table-bordered table-condensed table-striped table-hover">
                <thead>
                <tr><th scope="col">Masa</th></tr>
                </thead>
                <tbody>    
                    {tablesList}
                </tbody>
              </table>
            </div> 

            <div className="col-9">

            <div className="row CardItem"> 

            {cards}

            </div>

            </div>

           



        </div>


            
        </div>);
    }
}
 
export default TableList;