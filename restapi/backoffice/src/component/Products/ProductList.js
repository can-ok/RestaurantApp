import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Button} from 'reactstrap';
import {GrFormAdd} from 'react-icons/gr'
import ProductsService from '../../api/ProductsService'

import PageLoader from '../PageLoader';

import AppContext from '../../AppContext';

class ProductList extends Component {
    state = {
                items:[] ,
                loading:true
            }
    
    static contextType=AppContext;

    componentDidMount(){

        let appContext=this.context;
        let token=appContext.appState.token?appContext.appState.token:localStorage.getItem('token')

        ProductsService.token=token;
        
        ProductsService.getProduct("drinks")
        .then((response)=>{
            
            return response.json();
        }).then((data)=>{

            this.setState({
                items:data,
                loading:false
            });
        }).catch((error)=>{

            console.error("Error :",error)
        })
    }
    //http://localhost:8080/delete/food/1


    handle_detele=(itemId)=>{
   //http://localhost:8080/delete/food/1
   ProductsService.deleteProduct("drink",itemId)
    .then((response)=>{
        
        return response.json();

    } ).then((data)=>{

        this.setState({
            items:data
        });
        

    })
    .catch((error) => {
        console.error('Error:', error);
    });

    }



    handle_filterProducts=(category)=>{

        const items=this.state.items.filter( item=>
            item.productcategory.name ==category
         )

         this.setState({
             items
         })
    }

    render() { 

        const {items}=this.state;
        //const type="drinkDTO";
        let listTable=null;
        if(items.length>0){
        listTable= items.map( (item)=>

        <tr key={item.id}>
                    <td>{item.id}</td>
                    <td><Link to={`/description/${"drink"}/${item.id}`} >{item.title} </Link></td>
                    <td>{item.description}</td>
                    <td><img src={'data:image/png;base64,'+item.media.fileContent} width="75" /></td>
                    <td>
                    {item.productcategory.map((category)=><Link onClick={()=>this.handle_filterProducts(category.name)} >{category.name} </Link>  )}
                        <Link onClick={()=>this.handle_filterProducts(item.productcategory.name)} >{item.productcategory.name}</Link></td>
                    <td>{item.price.toString()}</td>

                    <td><Link to={`/update/${"drink"}/${item.id}`}   className="btn btn-warning">Edit</Link></td>
                    <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)}> Delete </Button></td>
         </tr>
          );
       }
    
       

        return ( <div>

            <PageLoader loading={this.state.loading} />
            <div className="mb-3">
            <strong>Drink List</strong> 
            <Link className="btn float-right" to="/add/drink"><GrFormAdd size='1rem'/><strong>Add Drink</strong></Link>

            </div>

            <table className="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>İsim</th>
                    <th>Açıklama</th>
                    <th>Görsel</th>
                    <th>Kategori</th>
                    <th>Fiyat</th>
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                {listTable}
                </tbody>
            </table>
            
            
        </div> );
    }
}
 
export default ProductList;