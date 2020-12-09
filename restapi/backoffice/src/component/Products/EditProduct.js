import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import ProductsService from '../../api/ProductsService'
import Select from 'react-select';
import CategoryService from '../../api/CategoryService';


class EditProduct extends Component {
    state = {id:this.props.match.params.id,
        type:this.props.match.params.type,
        itemTitle:"",
        itemDescription:"",
        productCategory:"",
        price:"",
        optionsCategory:[],
        selectedCategories:[]

    }


    componentDidMount(){

        let id=this.props.match.params.id
        let type=this.state.type

      
        ProductsService.getProductbyId(id,type)
        .then((response)=>{
            
            //this.setState({response})
            //response=response.json()
            //console.log(response)
            return response.json();
        }
        ).then((data)=>{

            
            
            this.setState({
                itemTitle:data.title,
                id:data.id,
                itemDescription:data.description,
                productCategory:data.productCategory,
                price:data.price,
            })

        })
        .catch((error) => {
            console.error('Error:', error);
        });



        
        CategoryService.getCategories()
        .then((response)=>{
            return response.json()
        })
        .then((data)=>{

            this.setState({
            optionsCategory:data
            })
        })


    }

    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    
    }

    handleSelectChange=(items)=>{
        //console.log("selected",items)
        let is= Array.isArray(items)? items.map(item=>{
            
            //console.log(item)
            return(item)
        }):[]
        console.log("is",is)

        this.setState({
            selectedCategories:is
        })
    }

    handleUpdate=()=>{

        //let id=this.props.match.params.id
        let type=this.props.match.params.type

        ProductsService.updateProduct(type,this.state)
        .then((response)=>{
            
        
            console.log(response)
        })
        .catch((error) => {
            console.error('Error:', error);
            });

    }


    render() { 

        const {itemTitle,itemDescription,selectedCategories,price}=this.state;
        
        const categoryOptions=this.state.optionsCategory.map((item)=>{
            return({label:item.name,id:item.id})
        })
        
        return (   <div>

            <Form>
            
            <FormGroup>
              <Label>Title:
              <Input name="itemTitle" type="text"  onChange={this.handleInputChange} value={itemTitle}/></Label>
            </FormGroup>
            <FormGroup>
              <Label>Description:
              <Input name="itemDescription" type="text"  onChange={this.handleInputChange} value={itemDescription}/></Label>
            </FormGroup>
            <FormGroup>
            <Label>Category:
              
            </Label>
            <Select options={categoryOptions} value={selectedCategories}  onChange={this.handleSelectChange} isMulti isClearable/>

            </FormGroup>
            <FormGroup>
              <Label>Price:
              <Input name="price" type="text"  onChange={this.handleInputChange} value={price}/></Label>
            </FormGroup>


            <Link to="/" onClick={this.handleUpdate} className="btn btn-warning">Update</Link>
            <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>
        
        </div>);
    }
}
 
export default EditProduct;