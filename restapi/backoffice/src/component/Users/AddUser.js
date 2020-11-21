import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";

import axios from 'axios';

class AddUser extends Component {
    state = { userName:"",userPass:"",userRole:"" }


    mySubmitHandler=()=>{

        const {userName,userPass,userRole}=this.state;
        var userCandidate={
            "name":userName,
            "password":userPass,
            "role":userRole
        }
        //http://localhost:8080/users/save

        axios.post("http://localhost:8080/users/save",userCandidate)
        .then((response)=>{
            console.log(response)
        }).catch((error)=>{

            console.log(error)
        })

    }

    handleInputChange=(event)=>{

        const target=event.target;
        const name=target.name;

        this.setState({
            [name]:target.value
        })
    }


    render() {
        return ( <Form>
           
            <FormGroup>
              <Label>Name:
              <Input name="userName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Password:
              <Input name="userPass" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>
            <FormGroup>
              <Label>Role:
              <Input name="userRole" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>

            <Link to="/users" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/users" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> );
    }
}
 
export default AddUser;