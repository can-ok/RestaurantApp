import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {ListGroup,ListGroupItem,Button} from 'reactstrap';
import {GrFormAdd} from 'react-icons/gr'


class FoodList extends Component {
    state = { 
        items:[]
     }

    componentDidMount(){


        fetch("http://localhost:8080/food")
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
        //http://localhost:8080/delete/food/1
         fetch("http://localhost:8080/delete/food/"+itemId
         ,{
     
             method: 'DELETE',
             headers: {
                 'Accept': 'application/json',
                 'Content-Type': 'application/json'
             },
         })
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
        const listItem= items.map( (item)=>
        
        <div className="d-flex mb-3 mt-2 border">

        <Link to={`/description/${"food"}/${item.id}`} >
            
            {item.title} 
        </Link>

        <div className="ml-auto">
                <Link to={`/update/${"food"}/${item.id}`}   className="btn btn-warning mr-1">Edit</Link>
                <Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} > Delete </Button>
                </div>

        </div>)

        return ( 
            <div>

            <div className="mb-3">
            <strong>Food List</strong>
            <Link className="btn float-right" to="/add/food"><GrFormAdd size='1rem'/><strong>Add Food</strong></Link>

            </div>

            <div>
                {listItem}
            </div>
                    
        </div>
         );
    }
}
 
export default FoodList;