import React, { Component } from 'react';
import '../App.css'
import { AiOutlinePlusCircle,AiOutlineMinusCircle } from 'react-icons/ai';
import { Label } from 'reactstrap';

import OrdersService from '../api/OrdersService';

import AppContext from '../AppContext';


export default class Basket extends Component {
    constructor(props){
        super(props);
        this.state = {
            cartItems:props.cartItems,
        };
      }

      static contextType=AppContext;

   

    handleBaskesIncrement=(item,cartItems)=>{

     for (let i in cartItems) {
        if ((cartItems[i].id == item.id) && (cartItems[i].productcategory.name == item.productcategory.name )) {
            cartItems[i].count++;
            this.setbasket(cartItems)

           break; //Stop this loop, we found it!
        }
      }

      this.setState({
          cartItems
      })


    }




    handleBaskesStateDecremet=(event,item,cartItems)=>{

        for (let i in cartItems) {  
           if ((cartItems[i].id == item.id) && (cartItems[i].productcategory.name == item.productcategory.name )) {
               if(cartItems[i].count<=1)
               {
                cartItems[i].count--;
                this.props.handleRemoveFromCart(event,item)

               }
               else{
                cartItems[i].count--;
                
                this.setbasket(cartItems)
               }
                
              break; //Stop this loop, we found it!
           }
         }
   
         this.setState({
             cartItems
         })
   
   
    }



    setbasket=(cartItemList)=>{

        let appContext=this.context;
        let tableContext=appContext.appState.table
        console.log("table context",tableContext)

        
        let item=localStorage.getItem(tableContext)
        item=JSON.parse(item)
        let basketItem;
        //console.log(item.table)
        
        //if contains table already

        if(item===null){
            basketItem={
                'table':tableContext,
                'products':cartItemList
            };
            localStorage.setItem(tableContext,JSON.stringify(basketItem))

        }
        else{
            if(item.table==tableContext){

                basketItem={
                    'table':tableContext,
                    'products':cartItemList
                };
               
            }
           

            localStorage.setItem(tableContext,JSON.stringify(basketItem))

        }
        

      
    }

    
    handleClick=(event,price,items)=>{
        var data=[]


        let appContext=this.context;
        let token=appContext.appState.token? appContext.appState.token:localStorage.getItem('token')

        let tableContext=appContext.appState.table
        let waiterContext=appContext.appState.waiter
        let customerContext=appContext.appState.customer
        console.log("customer "+customerContext)

        items.forEach(item => {
            //delete item
            this.props.handleRemoveFromCart(event,item)

            var jsonData={
                "productId":item.id,
                "productCount":item.count,
                "totalPrice":price,
                "paymentType":"cash",
                "orderTable":tableContext,
                "waiterId":waiterContext,
                "customerId":customerContext
            }
            
            data.push(jsonData);
        });

       

        
        OrdersService.token=token;
        
        OrdersService.saveOrders(data)
        .then((response)=>{

            console.log(response)
           
            alert("Sipariş verildi")
            
             //clean context
            let appState={...this.context.appState}
            appState.table=null
            this.context.setAppState(appState)
                
            localStorage.removeItem(tableContext)
        })
        
        .catch((err)=>{
            console.error(err)
        })

       

       

        

        //localStorage.removeItem('table')

        //this.props.history.push("/menu")

       // window.location='/menu'
    }
        


    render() { 
    
        const cartItems=this.props.cartItems;
        let carts;
        if(cartItems.length>0)
        {
            carts=cartItems.map( (item)=>

            <li className="mt-3 border-bottom">
                
                
                <label className="float-left"  onClick={()=>this.handleBaskesIncrement(item,cartItems)}><AiOutlinePlusCircle size={24} /></label>
                <label>
                <label>{item.title} </label>
                <label className="ml-1">{item.count} Adet </label>
                <label className="ml-1"> {item.count*item.price} TL</label>
                </label>
                

                {/*<button className="btn btn-danger" onClick={(e)=>this.props.handleRemoveFromCart(e,item)}>X</button>*/}
                <label className="float-right" onClick={(event)=>this.handleBaskesStateDecremet(event,item,cartItems)}><AiOutlineMinusCircle size={24}/> </label>

            </li>

        )
        }

        return (
            <div className="border border-secondary rounded ">
                
                
                   
        <div className="basketItem basketPaket">
            <ul className="descriptionItem">
                {carts}
            </ul>
            </div>   
        
        <div className="border-top border-primary"></div>

        <div className="mt-2 mb-2">
        <label>Toplam: {cartItems.reduce((a,c)=>a+c.price*c.count,0)} TL</label>

        <button className="btn btn-danger mr-2 float-right" onClick={(event)=>this.handleClick(event,cartItems.reduce((a,c)=>a+c.price*c.count,0),this.props.cartItems)}>Sipariş</button>
        </div>
      

        </div>

          );
    }
}
 
