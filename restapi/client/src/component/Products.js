import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {ListGroup, ListGroupItem} from 'reactstrap';
import '../App.css'

import ProductList from "./ProductsList";
import Basket from "./Basket";

class Production extends Component {
    state = {  items:[] ,
               value:"",
               allCategories:[],
               cartItems:[]             
    }




    componentDidMount(){
        
        this.getItems()

        //http://localhost:8080/getCategories
        fetch("http://localhost:8080/getCategories")
        .then((response)=>{
            
            return response.json();
        }).then((data)=>{

            this.setState({
                allCategories:data
            });
        }).catch((error)=>{

            console.error("Error :",error)
        })


    }
 /*    handleChange=(event)=>{
        
        this.setState({value:event.target.value})
    }
     */
    
    getItems=()=>{

            //http://localhost:8080/drinks
        fetch("http://localhost:8080/getProducts")
        .then((response)=>{
            
            return response.json();
        }).then((data)=>{

            this.setState({
                items:data
            });
        }).catch((error)=>{

            console.error("Error :",error)
        });

    }

   
    getItemCategory=(specificCategory)=>{

        fetch(`http://localhost:8080/category/${specificCategory}`)
        .then((response)=>{
            

            return response.json();
            }
        ).then((data)=>{
        
            this.setState({
                items:data
            })
    
        })
        .catch((error) => {
            console.error('Error:', error);
            });
    }


    handleRemoveFromCart=(event,item)=>{

        this.setState(state=>{
            
            const cartItems=state.cartItems.filter(element=> element.id !== item.id)
            return {cartItems}
        });

    }


    handleAddToCart=(e,product)=>{

        const itemFoundIndex = this.state.cartItems.findIndex(
            cp => cp.id === product.id
          );


          let cartItemList;

          if (itemFoundIndex !== -1) {
            //this.successfullCartToast();
            // map cart item array and update item at found index
            cartItemList = this.state.cartItems.map((item, i) =>
              i === itemFoundIndex ? { ...item, count: item.count+1 } : item
            );
          }
            else {
                // shallow copy into new array, append new item
                cartItemList = [...this.state.cartItems, {...product,count:1}];
              }

        this.setState({ cartItems:cartItemList });

    
    /*     this.setState( state=>{

            const cartItems=state.cartItems
            let productAlreadyInCart=false;
            cartItems.forEach(element => {

                if(element.id===product.id){
                    productAlreadyInCart=true;
                    element.count++;
                }

            });

            if(!productAlreadyInCart){

                cartItems.push({...product,count:1})
            }

            return cartItems;
        }); */
    }
    

    render() { 
        

        const catagoiresItem=this.state.allCategories.map( (category)=>
        

        <ListGroupItem  tag="button" action onClick={()=>this.getItemCategory(category)}>
            <Link>{category} </Link>
        </ListGroupItem>
        
        );

    
        return ( <div>

        <div class="row">
            <div className="col-sm float-left mt-2">
            <ListGroup className="Category_List">
                <ListGroupItem  tag="button" action  onClick={()=>this.getItems()}>
                <Link>All</Link>
                </ListGroupItem>
            {catagoiresItem}
            </ListGroup>
            </div>

            <div className="col-6">

            <ProductList handleAddToCart={this.handleAddToCart} items={this.state.items} /> 

             </div>

            <div className="col-sm">

            <Basket cartItems={this.state.cartItems} handleRemoveFromCart={this.handleRemoveFromCart} />
            </div>

         </div>   

        </div>);
    }
}
 
export default Production;