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
        const listTable= items.map( (item)=>

        <tr key={item.id}>
                    <td>{item.id}</td>
                    <td><Link to={`/description/${"drink"}/${item.id}`} >{item.title} </Link></td>
                    <td>{item.description}</td>
                    <td>{item.productCategory}</td>
                    <td>{item.price.toString()}</td>

                    <td><Link to={`/update/${"drink"}/${item.id}`}   className="btn btn-warning">Edit</Link></td>
                    <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)}> Delete </Button></td>
         </tr>


        );

        return ( <div>


            <div className="mb-3">
            <strong>Drink List</strong> 
            <Link className="btn float-right" to="/add/drink"><GrFormAdd size='1rem'/><strong>Add Drink</strong></Link>

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
            
            
        </div> );
    }
}
 
export default DrinkList;