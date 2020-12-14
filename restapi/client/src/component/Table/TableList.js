import React, { Component } from 'react';
import TableService from '../../api/TableService';
import WaiterService from '../../api/WaiterService';
import Modal from './Modal';

import './Table.css'

import PageLoader from '../PageLoader.js';

import AppContext from '../../AppContext';


class TableList extends Component {
    state = { tablesCategory:[],
              tables:[],
              showModal:false,
              waiters:[],
              loading:true
                }

    static contextType=AppContext;


    componentDidMount(){

        

        let appContext=this.context;
        let token=appContext.appState.token? appContext.appState.token:localStorage.getItem('token')
        console.log(token)
        
        TableService.token=token;

        TableService.getTables().then((response)=>{

            return response.json()
        }).then((data)=>{

            this.setState({
                tablesCategory:data,
                loading:false

            });
        })


       
        
        WaiterService.token=token;

        WaiterService.getAllWaiters().then((response)=>{

            this.setState({
                waiters:response.data,
                loading:false
            })
        })

       

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
        //localStorage.setItem("table",tableName)

        let appState={...this.context.appState}
        appState.table=tableName;
        this.context.setAppState(appState)
        
        this.setState({
            showModal:true
        })

    }

    selectReservedTable=(tableName)=>{
        
        let appState={...this.context.appState}
        appState.table=tableName;
        this.context.setAppState(appState)
        
        this.props.history.push("/products")

        //window.location="/products" 
    }

    selectWaiter=(item)=>{
        
        
        console.log(item)
        this.setState({showModal:false})
        
        let appState={...this.context.appState}
        appState.waiter=item.value
        this.context.setAppState(appState)

        //sessionStorage.setItem('waiter',value.id)


        this.props.history.push("/products")

        //window.location="/products" 

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
             <PageLoader loading={this.state.loading} />
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