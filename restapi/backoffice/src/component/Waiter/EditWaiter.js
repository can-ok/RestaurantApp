import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";

import WaiterService from '../../api/WaiterService';
class EditWaiter extends Component {
    state = { waiterName:""  }



    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    }


    mySubmitHandler=()=>{     
        
        let id=this.props.match.params.id


        WaiterService.updateWaiter(id,this.state).then((res)=>{
            console.log(res)
        }).catch((err)=>{console.log(err)})

        this.props.history.push("/waiters")
    }

    render() { 
        return ( <Form>
           
            <FormGroup>
              <Label>Name:
              <Input name="waiterName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
          

            <Link onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/tables" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>  );
    }
}
 
export default EditWaiter;