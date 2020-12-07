import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";

import WaiterService from '../../api/WaiterService';

class AddWaiter extends Component {
    state = { waiterName:"" }

    mySubmitHandler=()=>{     
      
        WaiterService.addWaiter(this.state).
        then((response)=>{

            window.location="/waiters"
            return response.json()
        }).catch((err)=>{

            console.log(err)
        })

        
    }
    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    }


    render() { 
        return ( <Form>
           
            <FormGroup>
              <Label>Name:
              <Input name="waiterName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            
              

            <Link to="/waiters" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/waiters" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> );
    }
}
 
export default AddWaiter;