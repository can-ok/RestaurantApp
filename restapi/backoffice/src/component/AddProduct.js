import React,{Component} from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";

class AddProduct extends Component {
    state = { itemTitle:"",
              itemDescription:"",
              productCategory:"",
              price:"",
              productType:this.props.match.params.type,

            }


    //http://localhost:8080/add/drink


    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    
    }


    mySubmitHandler=()=>{
        console.log(this.state.productType)

        const type=this.state.productType
        
        var data={
            "title":this.state.itemTitle,
            "description":this.state.itemDescription,
            "price":this.state.price,
            "productCategory":this.state.productCategory
            };


        fetch("http://localhost:8080/add/"+type,{

        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
        })
        .then((response)=>{
            
        
            console.log(response);



         })
        .catch((error) => {
            console.error('Error:', error);
          });
        
    }


    render() { 
        return ( <Form>
           
            <FormGroup>
              <Label>Title:
              <Input name="itemTitle" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Description:
              <Input name="itemDescription" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>
            <FormGroup>
              <Label>Category:
              <Input name="productCategory" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>

            <FormGroup>
              <Label>Price:
              <Input name="price" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>


            <Link to="/" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> );
    }
}
 
export default AddProduct;