import React, { Component } from 'react';
import '../App.css'
import { AiOutlinePlusCircle,AiOutlineMinusCircle } from 'react-icons/ai';


export default class Basket extends Component {
    state = { 
            cartItems:this.props.cartItems
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

    
    handleClick=()=>{


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
                                    
                                    <label className="mr-2" onClick={()=>this.handleBaskesState(item,cartItems)}><AiOutlinePlusCircle size={18} /></label>
                                    <label>{item.title} :</label>
                                    <label className="ml-2">{item.count} </label>
            
                                    {/*<button className="btn btn-danger" onClick={(e)=>this.props.handleRemoveFromCart(e,item)}>X</button>*/}
                                    <label className="ml-2" onClick={(event)=>this.handleBaskesStateDecremet(event,item,cartItems)}><AiOutlineMinusCircle size={18}/> </label>

                                </li>

                            )}
                        </ul>
                            
                            <div className="mt-2 mb-2">
                            <label>Total: {cartItems.reduce((a,c)=>a+c.price*c.count,0)}</label>
                            <button className="btn btn-danger float-right" onClick={()=>this.handleClick()}>Order</button>
                            </div>
                    </div>   
                }

            </div>

          );
    }
}
 
