import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Label, ListGroup, ListGroupItem} from 'reactstrap';
import '../../App.css'

import ProductList from "./ProductsList";
import Basket from "../Basket";

import ProductService from '../../api/ProductService';
import CategoryService from '../../api/CategoryService';


import AppContext from '../../AppContext';

class Production extends Component {
    state = {  items:[] ,
               value:"",
               categories:[],
               cartItems:[]             
    }



    static contextType=AppContext;

    componentDidMount(){
        

        let appContext=this.context;
        let token=appContext.appState.token
        let tableContext=appContext.appState.table;
        console.log(token)

        CategoryService.token=token;
        ProductService.token=token;

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

        
        if(tableContext===null || sessionStorage.getItem('waiter')===null){
            
            this.props.history.push("/table");

        }
        else{
        //get items from localStorage
        let item=localStorage.getItem(tableContext)
        if(item!=null){
            item=JSON.parse(item)
            this.setState({
                cartItems:item.products
            })
            }
       
        }

       

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
    
        }).catch((error) => {
            console.error('Error:', error);
            });

    }


    handleRemoveFromCart=(event,item)=>{


        this.setState(state=>{

            const cartItems=state.cartItems.filter(element=> (element.title !== item.title))
            this.setbasket(cartItems)
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

            //override
           

            this.setbasket(cartItemList);

          }
            else {
                // shallow copy into new array, append new item
                cartItemList = [...this.state.cartItems, {...product,count:1}];
                console.log("cari")

              
                this.setbasket(cartItemList)
                
              }

        this.setState({ cartItems:cartItemList });
    }


    setbasket=(cartItemList)=>{

        let appContext=this.context;
        let tableContext=appContext.appState.table;

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
    

    render() { 

        let appContext=this.context;
        let tableContext=appContext.appState.table;

        let item=localStorage.getItem(tableContext)
        item=JSON.parse(item)
        console.log(item)
        

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
                <h5 className='ml-3 mt-2 border'> <strong>Durum:</strong> {tableContext}</h5>

                </div>

        <div className="row">
            <div className="col-2 float-left mt-2 ml-2 row">
            
            <ListGroup className="Category_List">
                <ListGroupItem  tag="button" action  onClick={()=>this.getItems()}>
                <Link>Hepsi</Link>
                </ListGroupItem>
            {categoriesList}
            </ListGroup>
            </div>

            <div className="col-6 CardItem">

            <ProductList handleAddToCart={this.handleAddToCart} items={this.state.items} /> 

             </div>

            <div className="col-4  mt-2">

            <Basket cartItems={this.state.cartItems} handleRemoveFromCart={this.handleRemoveFromCart} />
            </div>

         </div>   

        </div>);
    }
}
 
export default Production;