import React, { Component } from 'react';
import {Form,FormGroup,Label,Input, Button} from 'reactstrap';
import {Link} from "react-router-dom";
import CategoryService from '../../api/CategoryService';

class AddCategory extends Component {
    state = { itemTitle:""}

    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    }

    mySubmitHandler=()=>{

        

        CategoryService.addgetCategories(this.state.itemTitle)
        .then((response)=>{
            console.log(response);
            window.location="/categories"; //full reload
            })
        .catch((error) => {
            console.error('Error:', error);
            });
        
    }
        
        
   
    render() { 
        return ( 
            <Form>
           
            <FormGroup>
              <Label>İsim:
              <Input name="itemTitle" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
        


            <Button  onClick={this.mySubmitHandler} className="btn btn-success">Submit</Button>
            <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> 
        );
    }
}
 
export default AddCategory;