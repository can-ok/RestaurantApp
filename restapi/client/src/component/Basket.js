import React, { Component } from 'react';
import '../App.css'
import { AiOutlinePlusCircle,AiOutlineMinusCircle } from 'react-icons/ai';
import { Label } from 'reactstrap';

import OrdersService from '../api/OrdersService';

import AppContext from '../AppContext';

import Modal from './Modal';
import { withRouter } from 'react-router-dom';

class Basket extends Component {
    constructor(props){
        super(props);
        this.state = {
            cartItems:props.cartItems,
            paymentType:'',
            showModal:false,
            paymentStatus:null,
            totalPrice:null,
            customerName:''
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

      this.setState({ cartItems})

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
             cartItems})
   

    }



    setbasket=(cartItemList)=>{

        let appContext=this.context;
        let tableContext=appContext.appState.table
        console.log("table context",tableContext)

        
        let item=localStorage.getItem(tableContext)
        item=JSON.parse(item)
        let basketItem;
     
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

    selectPaymentType=(item)=>{
        let type=item.value
        this.setState({paymentType:type})

        if(type=='Nakit'){
            this.setState({paymentStatus:true})
        }
        if(type=='Kredi Kartı'){
            this.setState({paymentStatus:false})
        }
        
    }
    orderWithCash=(name,charge)=>{
        console.log(name,charge)
        this.setState({customerName:name})
        this.sendOrder()
    }


    orderWithCard=(cardNumber,charge)=>{
        console.log(cardNumber,charge)
        this.setState({cardNumber:cardNumber})
        this.sendOrder()
    }
    
    handleClick=(price)=>{
        this.setState({totalPrice:price})
        this.setState({showModal:true})

    
    }

    sendOrder=()=>{
        var data=[]
        let appContext=this.context;
        let token=appContext.appState.token? appContext.appState.token:localStorage.getItem('token')
        let tableContext=appContext.appState.table
        let waiterContext=appContext.appState.waiter
        let customerContext=appContext.appState.customer
        OrdersService.token=token;

        console.log("customer "+customerContext)
               
        console.log(this.props.cartItems)
        let items=this.props.cartItems

        items.forEach(item => {
            //delete item
            this.props.handleRemoveFromCart(item)

            var jsonData={
                "productId":item.id,
                "productCount":item.count,
                "productPrice":item.price,
                "orderId":null
            }
            
            data.push(jsonData);
        });  

        

        let order={
            "paymentType":this.state.paymentType,
            "orderTable":tableContext,
            "waiterId":null,
            "customerId":null,
            "totalPrice":this.state.totalPrice,
            "orderItems":null
        }

        let model={
            "orderItemsDtoList":data,
            "ordersDto":order,
            "waiterId":waiterContext==null?null:waiterContext.id,
            "customerId":customerContext
        }
        
        console.log(model)

       OrdersService.saveOrders(model)
        .then((response)=>{

            console.log(response)

            this.setState({showModal:false})

           
            alert("Sipariş verildi")
            
             //clean context
            let appState={...this.context.appState}
            appState.table=null
            appState.waiter=null
            this.context.setAppState(appState)
            localStorage.removeItem(tableContext)
            this.props.history.push('/table')

        })
        
        .catch((err)=>{
            console.error(err)
        })  
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

        <button className="btn btn-danger mr-2 float-right" onClick={(event)=>this.handleClick(cartItems.reduce((a,c)=>a+c.price*c.count,0))}>Sipariş</button>
        </div>

            {this.state.showModal && <Modal open={this.state.showModal} selectPaymentType={this.selectPaymentType}
             onClose={()=>this.setState({showModal:false}) }
            paymentStatus={this.state.paymentStatus} 
            orderWithCash={this.orderWithCash} 
            orderWithCard={this.orderWithCard}/> }

        </div>

          );
    }
}
 
export default withRouter(Basket)
