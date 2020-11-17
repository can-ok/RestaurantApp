import React, { Component } from 'react';
import '../App.css'
//import {FaFacebook} from 'react-icons/fa'
import {Link} from "react-router-dom";

import '../App.css'


class ProductDescription extends Component {
    state = { 
        id:this.props.match.params.id,
        type:this.props.match.params.type,
        item:""
     }


     componentDidMount(){

        if(this.state.id==-1){
            return false;
        }
        else{
            let id=this.props.match.params.id
            let type=this.state.type

            console.log("id:"+id)
            console.log("tpye:"+type)


           
            fetch(`http://localhost:8080/${type}/${id}`)
            .then((response)=>{
                
                //this.setState({response})
                //response=response.json()
                //console.log(response)
                return response.json();
             }
            ).then((data)=>{
    
                  
    
                   this.setState({
                       item:data
                    })
    
            })
            .catch((error) => {
                console.error('Error:', error);
              });
        }

    }
    render() {
        
        return (  <div><h1>Description</h1>

        <ul className="descriptionItem">
        <li><label><strong>Title:</strong> {this.state.item.title}</label></li>
        <li><label><strong>Price:</strong> {this.state.item.price} </label></li> 
        <li><label><strong>Description:</strong> {this.state.item.description}</label></li>
        <li><label><strong>Category:</strong> {this.state.item.productCategory}</label></li> 
        
        <li><Link to="/" className="btn btn-dark ml-2 float-right">Back</Link></li>
        </ul>

        </div>);
    }
}
 
export default ProductDescription;