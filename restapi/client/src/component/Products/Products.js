import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {ListGroup, ListGroupItem} from 'reactstrap';
import '../../App.css'

import ProductList from "./ProductsList";
import Basket from "../Basket";

import ProductService from '../../api/ProductService';
import CategoryService from '../../api/CategoryService';

class Production extends Component {
    state = {  items:[] ,
               value:"",
               categories:[],
               cartItems:[]             
    }




    componentDidMount(){
        
        this.getItems()

        //http://localhost:8080/getCategories

        CategoryService.getCategories()
        .then((respose)=>{

            return respose.json()
        }).then((data)=>{

            this.setState({
                categories:data
            })
        })
        .catch((error)=>{

            console.error("Error :",error)
        })

    }

    handleChange=(event)=>{
        
        this.setState({value:event.target.value})
    }
    
    
    getItems=()=>{

            //http://localhost:8080/drinks
        ProductService.getAllProducts()
        .then((response)=>{
            
            this.setState({
                items:response.data
            });


        }).catch((error)=>{

            console.error("Error :",error)
        });

    }

   
    getItemCategory=(specificCategory)=>{

        ProductService.getProductsByCategory(specificCategory)
        .then((response)=>{
            
            
            this.setState({
                items:response.data
            })
    
        }
        )
        .catch((error) => {
            console.error('Error:', error);
            });
    }


    handleRemoveFromCart=(event,item)=>{


        this.setState(state=>{

            
            const cartItems=state.cartItems.filter(element=> (element.title !== item.title))
            return {cartItems}
        });

    }


    handleAddToCart=(e,product)=>{

        const itemFoundIndex = this.state.cartItems.findIndex(
            cp => (cp.id === product.id)&& (cp.productcategory.id=== product.productcategory.id)
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

    
  
    }
    

    render() { 
        

        let categoriesList=null;

         if(this.state.categories.length>0){
            categoriesList=this.state.categories.map( (category)=>
        

            <ListGroupItem  tag="button" action onClick={()=>this.getItemCategory(category.id)}>
                <Link>{category.name} </Link>
            </ListGroupItem>
            
            );
        } 

    
        return ( <div>

        <div className="row">
            <div className="col-sm float-left mt-2 row">
            <ListGroup className="Category_List">
                <ListGroupItem  tag="button" action  onClick={()=>this.getItems()}>
                <Link>Hepsi</Link>
                </ListGroupItem>
            {categoriesList}
            </ListGroup>
            </div>

            <div className="col-6">

            <ProductList handleAddToCart={this.handleAddToCart} items={this.state.items} /> 

             </div>

            <div className="col-sm  mt-2">

            <Basket cartItems={this.state.cartItems} handleRemoveFromCart={this.handleRemoveFromCart} />
            </div>

         </div>   

        </div>);
    }
}
 
export default Production;