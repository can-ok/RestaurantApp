import React, { Component } from 'react';
import {Form,FormGroup,Label,Input, Button} from 'reactstrap';
import {Link} from "react-router-dom";
import CategoryService from '../../api/CategoryService';

import Select from 'react-select';

import MediaService from '../../api/MediaService';


class AddCategory extends Component {
    constructor(props) {
        super(props);
        this.state = { itemTitle:"" ,
                       itemDescription:"",
                       categoryMedia:[] ,
                       selectedMedia:[]
                        }

    }


    componentDidMount(){

        fetch("http://localhost:8080/media/getAll",{
            method:'GET'
        }).then((response)=>response.json())
        .then((data)=>{
            this.setState({
                categoryMedia:data
            })
        })
        .catch((err)=>console.log(err))


     
    }



    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    }

    mySubmitHandler=()=>{

    

        CategoryService.addCategories(this.state)
        .then((response)=>{
            console.log(response);
            //window.location="/categories"; //full reload

            this.props.history.push("/categories")
            })
        .catch((error) => {
            console.error('Error:', error);
            });
        
    }
        
        
    handleSelectChange=(item)=>{
        
        this.setState({
            selectedMedia:item
        })
    }
    render() { 


        const categoryOptions=this.state.categoryMedia.map((item)=>{
            return({label:<div>{item.name} <img src={'data:image/png;base64,'+item.fileContent} width="30" /> </div> ,value:item}
                )
        })


        return ( 
            <Form>
           
            <FormGroup>
              <Label>İsim:
              <Input name="itemTitle" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Açıklama:
              <Input name="itemDescription" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>

            <FormGroup>
              <Label>Media:</Label>
              <Select className="col-md-4" options={categoryOptions}  value={this.state.selectedMedia} onChange={this.handleSelectChange} />
            </FormGroup>
        


            <Button  onClick={this.mySubmitHandler} className="btn btn-success">Submit</Button>
            <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> 
        );
    }
}
 
export default AddCategory;