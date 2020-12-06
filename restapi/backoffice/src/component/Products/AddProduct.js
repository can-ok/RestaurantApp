import React,{Component} from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import ProductsService from '../../api/ProductsService';

import CategoryService from '../../api/CategoryService';

class AddProduct extends Component {
    state = { itemTitle:"",
              itemDescription:"",
              productCategory:"",
              price:"",
              options:[],
              selectValue:1
            }


    //http://localhost:8080/add/drink


    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
        
        
    }


    componentDidMount(){

      CategoryService.getCategories()
      .then((response)=>{
          return response.json()
      })
      .then((data)=>{

        this.setState({
          options:data
        })
      })

    }


    mySubmitHandler=()=>{

        const type=this.props.match.params.type;
     

        ProductsService.addProduct(this.state,type)
        .then((response)=>{
            console.log(response);
         })
        .catch((error) => {
            console.error('Error:', error);
          });
        
    }


    render() { 

        const optionList=this.state.options.map((item)=>{
          return(<option key={item.id} value={item.id}>{item.name}</option>)
        })

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
             <select id = "dropdown" name="selectValue" value={this.state.selectValue}   onChange={this.handleInputChange}>
                {optionList}
              </select>
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