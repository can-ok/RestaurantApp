import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {ListGroup,ListGroupItem,Button} from 'reactstrap';
import {GrFormAdd} from 'react-icons/gr'

class DrinkList extends Component {
    state = {
                items:[] 
            }

    componentDidMount(){
        //http://localhost:8080/drinks
        fetch("http://localhost:8080/drinks")
        .then((response)=>{
            
            return response.json();
        }).then((data)=>{

            this.setState({
                items:data
            });
        }).catch((error)=>{

            console.error("Error :",error)
        })
    }
    //http://localhost:8080/delete/food/1


    handle_detele=(itemId)=>{
   //http://localhost:8080/delete/food/1
    fetch("http://localhost:8080/delete/drink/"+itemId
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
        //const type="drink";
        const listItem= items.map( (item)=>
        
        <div className="d-flex mb-3 mt-2 border">

        <Link to={`/description/${"drink"}/${item.id}`} >
            
            {item.title} 
        </Link>

        <div className="ml-auto">
                <Link to={`/update/${"drink"}/${item.id}`}   className="btn btn-warning mr-1">Edit</Link>
                <Button className="btn btn-danger"onClick={()=>this.handle_detele(item.id)}> Delete </Button>
                </div>

        </div>

        );

        return ( <div>


            <div className="mb-3">
            <strong>Drink List</strong> 
            <Link className="btn float-right" to="/add/drink"><GrFormAdd size='1rem'/><strong>Add Drink</strong></Link>

            </div>

            <div>
                {listItem}
            </div>
            
        </div> );
    }
}
 
export default DrinkList;