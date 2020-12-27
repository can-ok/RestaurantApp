import React, { Component } from 'react';
import {Form,FormGroup,Label,Input, Button} from 'reactstrap';
import {Link} from "react-router-dom";
import CategoryService from '../../api/CategoryService';
import Select from 'react-select';
import MediaService from '../../api/MediaService';

class EditCategory extends Component {
    state = {  itemTitle:"" ,
    itemDescription:"",
    categoryMedia:[] ,
    selectedMedia:[],
    id:this.props.match.params.id,}



    componentDidMount(){

        MediaService.getAllMedia()
        .then((response)=>response.json())
        .then((data)=>{
          this.setState({
            categoryMedia:data
          })
        })
  


        fetch("http://localhost:8080/category/get/"+this.state.id).then((response)=>response.json())
        .then((data)=>{
            console.log(data)

            this.setState({
                itemTitle:data.name,
                itemDescription:data.description
            })
        }).catch((err)=>console.log(err))
    }


    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    }

    handleSelectChange=(item)=>{
        
        this.setState({
            selectedMedia:item
        })
    }


    mySubmitHandler=()=>{
            
        CategoryService.updateCategory(this.state)
        .then((response)=>{
            
            console.log(response)
            this.props.history.push("/categories")

        })
        .catch((error) => {
            console.error('Error:', error);
            });
    }


    render() { 

        const {itemTitle,itemDescription}=this.state;
        const categoryOptions=this.state.categoryMedia.map((item)=>{
            return({label:<div>{item.name} <img src={'data:image/png;base64,'+item.fileContent} width="30" /> </div> ,value:item}
                )
        })
        return (   <Form>
           
            <FormGroup>
              <Label>İsim:
              <Input name="itemTitle" type="text"  onChange={this.handleInputChange}  value={itemTitle}/></Label>
            </FormGroup>
            <FormGroup>
              <Label>Açıklama:
              <Input name="itemDescription" type="text"  onChange={this.handleInputChange} value={itemDescription} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Açıklama:
              <Input name="itemDescription" type="text"  onChange={this.handleInputChange} value={itemDescription} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Media:</Label>
              <Select className="col-md-4" options={categoryOptions}  value={this.state.selectedMedia} onChange={this.handleSelectChange} />
            </FormGroup>

            <Button  onClick={this.mySubmitHandler} className="btn btn-success">Submit</Button>
            <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> );
    }
}
export default EditCategory;