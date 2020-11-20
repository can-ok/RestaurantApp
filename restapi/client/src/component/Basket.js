import React, { Component } from 'react';
import '../App.css'
import { AiOutlinePlusCircle,AiOutlineMinusCircle } from 'react-icons/ai';


export default class Basket extends Component {
    constructor(props){
        super(props);
        this.state = {
            cartItems:props.cartItems

        };
      }


   

    handleBaskesState=(item,cartItems)=>{

     for (let i in cartItems) {
        if (cartItems[i].id == item.id) {
            cartItems[i].count++;
           break; //Stop this loop, we found it!
        }
      }

      this.setState({
          cartItems
      })


    }




    handleBaskesStateDecremet=(event,item,cartItems)=>{

        for (let i in cartItems) {
           if (cartItems[i].id == item.id) {
               if(cartItems[i].count<=1)
               {
                cartItems[i].count--;
                this.props.handleRemoveFromCart(event,item)

               }
               else{
                cartItems[i].count--;
               }
                
              break; //Stop this loop, we found it!
           }
         }
   
         this.setState({
             cartItems
         })
   
   
       }

    
    handleClick=(event,price,items)=>{
        var data=[]

        items.forEach(item => {
            //delete item
            this.props.handleRemoveFromCart(event,item)

            var jsonData={
                "productId":item.id,
                "productCount":item.count,
                "totalPrice":price,
                "paymentType":"cash"
            }
            
            data.push(jsonData);
        });

        fetch("http://localhost:8080/orders/saveOrders",{


            method:'POST',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(data,)}
        ).then((response)=>{

            console.log(response)


        })
        
        .catch((err)=>{
            console.error(err)
        })
            

        
    }
        


    render() { 
    
        const cartItems=this.props.cartItems;
        return (
            <div className="border border-secondary rounded">
                {cartItems.length==0? "Basket is empty": "Basket"}
                {
                    cartItems.length>0 &&
                    <div>
                        <ul className="descriptionItem">
                            {cartItems.map( (item)=>

                                <li className="mt-3 border-bottom">
                                    
                                    <label className="mr-2 float-left" onClick={()=>this.handleBaskesState(item,cartItems)}><AiOutlinePlusCircle size={24} /></label>
                                    <label>{item.title} :</label>
                                    <label className="ml-2">{item.count} </label>
            
                                    {/*<button className="btn btn-danger" onClick={(e)=>this.props.handleRemoveFromCart(e,item)}>X</button>*/}
                                    <label className="ml-2 float-right" onClick={(event)=>this.handleBaskesStateDecremet(event,item,cartItems)}><AiOutlineMinusCircle size={24}/> </label>

                                </li>

                            )}
                        </ul>
                            
                            <div className="mt-2 mb-2">
                            <label>Total: {cartItems.reduce((a,c)=>a+c.price*c.count,0)}</label>
                            <button className="btn btn-danger float-right" onClick={(event)=>this.handleClick(event,cartItems.reduce((a,c)=>a+c.price*c.count,0),this.props.cartItems)}>Order</button>
                            </div>
                    </div>   
                }

            </div>

          );
    }
}
 
