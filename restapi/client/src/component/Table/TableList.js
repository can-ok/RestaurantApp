import React, { Component } from 'react';
import TableService from '../../api/TableService';
import WaiterService from '../../api/WaiterService';
import Modal from './Modal';

import './Table.css'

class TableList extends Component {
    state = { tablesCategory:[],
              tables:[],
              showModal:false,
              waiters:[],
              reservedTables:[]
                }




    componentDidMount(){

        TableService.getTables().then((response)=>{

            return response.json()
        }).then((data)=>{

            this.setState({
                tablesCategory:data
            });
        })

        WaiterService.getAllWaiters().then((response)=>{

            this.setState({
                waiters:response.data
            })
        })

        TableService.getReserved().then((response)=>response.json())
        .then((data)=>{
            
            this.setState({
                reservedTables:data
            })
        })
       
        .catch((err)=>{
            console.log(err)
        })

    }


    checkTableReserved=(tableName)=>{
        let {reservedTables}=this.state;

        for(let i=0; i<reservedTables.length; i++){

            if(reservedTables[i].Table==tableName){
                return [true,reservedTables[i].Count];
            }
        }

        return [false,0];
    }

    showCards=(table)=>{

       if(table.title==='Ayakta'){
        this.selectTable(table.title)

       }
       else{

        let tables=[]

        //console.log(table)

        for(let count=1; count<=table.tableCount; count++){
        let status,prodCount;

        if(localStorage.getItem(`${table.title} ${count}`)){
            console.log(`${table.title} ${count}`)
            status=true
            prodCount=0
            JSON.parse(localStorage.getItem(`${table.title} ${count}`)).products.forEach(element => {
                prodCount+=element.count
            });
        }
            
                                 
           
           let tableObject={'name':`${table.title} ${count}`,
                            'reservedStatus':status,
                            'count':prodCount}
            tables.push(tableObject)
            
        }
        
        this.setState({
            tables })
        }
    }


    selectTable=(tableName)=>{
        localStorage.setItem("table",tableName)
        
        this.setState({
            showModal:true
        })

    }

    selectReservedTable=(tableName)=>{
        
        alert('Secili Masa',tableName)
    }

    selectWaiter=(value)=>{
       
        console.log(value)
        this.setState({showModal:false})
    
        sessionStorage.setItem('waiter',value.id)

        window.location="/products" 

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
                
              if(!item.reservedStatus){
                return(<div key={item.name} className="col-md-4  border Card" onClick={()=>this.selectTable(item.name)} > 
                
                <h5 className="align-items-center">
                {item.name}
                </h5>
                </div>)
              }
              else{
                return(<div key={item.name} className="col-md-4  border Cardreserved" onClick={()=>this.selectReservedTable(item.name)} >
                    
                     <div className="float-right count">{item.count}</div>
                     <h5 className="align-items-center">{item.name}</h5>
                </div>)
              }

            })
        

        }


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
            
           { this.state.showModal&& <Modal open={this.state.showModal} 
                                        waiters={this.state.waiters}  selectWaiter={this.selectWaiter}
                                        onClose={()=>this.setState({showModal:false})}>
                
            </Modal>}

        </div>


            
        </div>);
    }
}
 
export default TableList;