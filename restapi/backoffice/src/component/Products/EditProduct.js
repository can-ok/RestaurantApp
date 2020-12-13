import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import ProductsService from '../../api/ProductsService'
import Select from 'react-select';
import CategoryService from '../../api/CategoryService';
import MediaService from '../../api/MediaService';


class EditProduct extends Component {
    state = {id:this.props.match.params.id,
        type:this.props.match.params.type,
        itemTitle:"",
        itemDescription:"",
        productCategory:"",
        price:"",
        optionsCategory:[],
        selectedCategories:[],
        mediaOptions:[],
        selectedMedia:[]

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

        MediaService.getAllMedia()
        .then((response)=>response.json())
        .then((data)=>{
          this.setState({
            mediaOptions:data
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
        let list= Array.isArray(items)? items.map(item=>{
            
            //console.log(item)
            return(item.value)
        }):[]


        this.setState({
            selectedCategories:list
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
            return({'label':item.name,'value':item})
        })

        const mediaList=this.state.mediaOptions.map((option)=>{
            return({'label':<div>{option.name}  <img src={'data:image/png;base64,'+option.fileContent} width="30" /></div> ,value:option})
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
            <Label>Category: </Label>
            <Select options={categoryOptions} value={categoryOptions.filter(obj=>selectedCategories.includes(obj.value))}  onChange={this.handleSelectChange} isMulti isClearable/>
            </FormGroup>

            <FormGroup>
            <Label>Media:
              </Label>
            <Select options={mediaList} value={this.state.selectedMedia}  onChange={(e)=>{this.setState({selectedMedia:e})}} isClearable/>
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