import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {ListGroup} from 'reactstrap';
import '../../App.css'
import ProductList from "./ProductsList";
import Basket from "../Basket";

import ProductService from '../../api/ProductService';
import CategoryService from '../../api/CategoryService';

import PageLoader from '../PageLoader.js';

import AppContext from '../../AppContext';

class Production extends Component {
    state = {  items:[] ,
               value:"",
               categories:[],
               selectedCategory:null,
               cartItems:[],
               loading:true,
               pageSize:6,
               pageCount:0,
               last:false
    }



    static contextType=AppContext;

    componentDidMount(){

        let appContext=this.context;
        let token=appContext.appState.token?appContext.appState.token:localStorage.getItem('token')
        let tableContext=appContext.appState.table;

        let waiterContext=appContext.appState.waiter
        
        console.log(tableContext)
        CategoryService.token=token;
        ProductService.token=token;

        this.getItemCategory(this.state.selectedCategory)

        //http://localhost:8080/getCategories

        CategoryService.getCategories()
        .then((respose)=>{

            return respose.json()
        }).then((data)=>{

            this.setState({
                categories:data,
                loading:false
            })
        })
        .catch((error)=>{

            console.error("Error :",error)
        })

        
       
        //get items from localStorage
        let item=localStorage.getItem(tableContext)
        if(item!=null){
            item=JSON.parse(item)
            this.setState({
                cartItems:item.products,
                loading:false
            })
            }
        

       

    }

    handleChange=(event)=>{
        
        this.setState({value:event.target.value})
    }
    
    
   

    
    getItemCategory=(specificCategory,e)=>{

        if(specificCategory==null){
            ProductService.getAllProducts(this.state.pageSize,this.state.pageCount)
            .then((response)=>{

                if(this.state.items.length>0)
                {
                    this.setState({
                        selectedCategory:null,
                        items:[...this.state.items,...response.data.content],
                        last:response.data.last
                    });
                }
                else{
                    this.setState({
                        selectedCategory:null,
                        items:response.data.content,
                        pageCount:0,
                        last:response.data.last
                    });

                }
    
            }).catch((error)=>{
                console.error("Error :",error)
            });
        }
        else{

        ProductService.getProductsByCategory(specificCategory,this.state.pageSize,this.state.pageCount)
        .then((response)=>{
            if(this.state.selectedCategory!=specificCategory){

                this.setState({
                    items:response.data.content,
                    selectedCategory:specificCategory,
                    pageCount:0,
                    last:response.data.last
                })

            }
            else{
                this.setState({
                    items:[...this.state.items,...response.data.content],
                    selectedCategory:specificCategory,
                    last:response.data.last
                })
            }

        }).catch((error) => {
            console.error('Error:', error);
            });
            
        }
        

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

    handleScroll=(e)=>{
        const bottom=e.target.scrollHeight-e.target.scrollTop===e.target.clientHeight;
        if(bottom && !this.state.last){
            this.setState({pageCount:this.state.pageCount+1})
            this.getItemCategory(this.state.selectedCategory,e)
        }
    }
    

    render() { 

        let appContext=this.context;
        let tableContext=appContext.appState.table;

        let categoriesList=null;

         if(this.state.categories.length>0){
            categoriesList=this.state.categories.map( (category)=>
            <div className="mt-3  border border-primary p-3 rounded" tag="button" action onClick={()=>this.getItemCategory(category.id)}>
                <Link>{category.name} <img src={'data:image/png;base64,'+category.categorymedia.fileContent} width="30" /></Link>
            </div>
            
            );
        } 

    
        return ( <div>
                <PageLoader loading={this.state.loading} />

                <div className="row">
                <h5 className='ml-3 mt-2 border'> <strong>Durum:</strong> {tableContext}</h5>

                </div>

        <div className="row">
            <div className="col-2 float-left mt-2 ml-2 row">
            
            <ListGroup className="Category_List">
                <div className="border border-primary p-3 rounded"  tag="button" action  onClick={()=>this.getItemCategory(null)}>
                <Link>Hepsi</Link>
                </div>
            {categoriesList}
            </ListGroup>
            </div>

            <div className="col-5 CardItem" onScroll={this.handleScroll}>
                <ProductList handleAddToCart={this.handleAddToCart} items={this.state.items} /> 

            </div>

            <div className="col-5  mt-2">
                <Basket cartItems={this.state.cartItems} handleRemoveFromCart={this.handleRemoveFromCart} />
            </div>

         </div>   

        </div>);
    }
}
 
export default Production;