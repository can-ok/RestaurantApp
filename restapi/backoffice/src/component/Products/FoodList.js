import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {ListGroup,ListGroupItem,Button} from 'reactstrap';
import {GrFormAdd} from 'react-icons/gr'


import ProductsService  from '../../api/ProductsService'

class FoodList extends Component {
    state = { 
        items:[]
     }

    componentDidMount(){
        ProductsService.getProduct("food")
        .then((response)=>{

            return response.json()
        }).then((data)=>{

            this.setState({
                items:data
            });
        }).catch((error)=>{
            console.error("Error:",error)
        })
    } 



    handle_detele=(itemId)=>{

        ProductsService.deleteProduct("food",itemId)
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

    render() { 

        const {items}=this.state;
     
        const listTable= items.map( (item)=>
        <tr key={item.id}>
                    <td>{item.id}</td>
                    <td><Link to={`/description/${"food"}/${item.id}`} >{item.title} </Link></td>
                    <td>{item.description}</td>
                    <td>{item.productCategory}</td>
                    <td>{item.price.toString()}</td>

                    <td><Link to={`/update/${"food"}/${item.id}`} className="btn btn-warning">Edit</Link></td>
                    <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Delete </Button></td>
         </tr>

       )

        return ( 
            <div>

            <div className="mb-3">
            <strong>Food List</strong>
            <Link className="btn float-right" to="/add/food"><GrFormAdd size='1rem'/><strong>Add Food</strong></Link>

            </div>
            <table className="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>price</th>
                    <th></th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                {listTable}
                </tbody>
            </table>
            
            
        </div>
         );
    }
}
 
export default FoodList;