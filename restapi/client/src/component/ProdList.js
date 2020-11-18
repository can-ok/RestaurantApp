import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {Button,Dropdown, DropdownToggle, DropdownMenu, DropdownItem  } from 'reactstrap';

class ProductionList extends Component {
    state = {  items:[] ,
               value:""             
    }


    componentDidMount(){
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
        })
    }
    handleChange=(event)=>{
        
        this.setState({value:event.target.value})
    }   

    render() { 

        const listItem= this.state.items.map( (item)=>

        <div className="col-md-3 mr-2 ml-2 mt-2 border">

            
        <img src="https://cdn.shopify.com/s/files/1/0070/7032/files/camera_56f176e3-ad83-4ff8-82d8-d53d71b6e0fe.jpg?v=1527089512" width="100" height="80" />
        

        <Link className="row justify-content-center">
        {item.title}
        </Link>

        <label className="row justify-content-center">{item.productCategory}</label>

        <div className="row justify-content-center">
            <form key={item.id} >
                
           <select key={item.id} value={this.state.value} onChange={this.handleChange}>
               <option value="1">1</option>
               <option value="2">2</option>
               <option value="3">3</option>
               <option value="4">4</option>
               <option value="5">5</option>
           </select>
           <div type="submit" value="Submit" className="btn btn-danger mt-2 d-flex">Order</div>
           </form>

            </div>
        </div>



        );

        return ( <div className="row">

                {listItem}

        </div>  );
    }
}
 
export default ProductionList;