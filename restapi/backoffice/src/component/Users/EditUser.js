import React, { Component } from 'react';
import axios from 'axios';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";

import UserService from '../../api/UserService'

class EditUser extends Component {
    state = {
        id:"",
        userName:"",
        userPass:"",
        userRole:"",
        enabled:"" }


    handleInputChange=(event)=>{

        const target=event.target;
        const name=event.target.name;

        this.setState({
            [name]:target.value
        })

    }    


    componentDidMount(){

        let id=this.props.match.params.id
        UserService.getUserById(id)
        .then((response)=>{

            console.log(response)
            this.setState({
                id:response.data.id,
                userName:response.data.userName,
                userPass:response.data.password,
                userRole:response.data.authority,
                enabled:response.data.enabled
            })

        })
        .catch((err)=>{

            console.error(err);
        })

    }

    handleUpdate=()=>{


        let userData={
            "id":this.state.id,
            "userName":this.state.userName,
            "password":this.state.userPass,
            "authority":this.state.userRole,
            "enabled":this.state.enabled
        }
        //http://localhost:8080/users/update/1
        
        UserService.updateUser(this.state.id,userData)
        .then((response)=>{
            
        
            console.log(response)
            })
        .catch((error) => {
            console.error('Error:', error);
            });

    }
    

    render() { 

        const {userName,userPass,userRole}=this.state;

        return ( <Form>
           
            <FormGroup>
              <Label>Username:
              <Input name="userName" type="text"  onChange={this.handleInputChange} value={userName} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Password:
              <Input name="userPass" type="text"  onChange={this.handleInputChange} value={userPass}/></Label>
            </FormGroup>
            <FormGroup>
              <Label>Authority:
              <Input name="userRole" type="text"  onChange={this.handleInputChange} value={userRole}/></Label>
            </FormGroup>

            <Link to="/users" onClick={this.handleUpdate} className="btn btn-success">Submit</Link>
            <Link to="/users" className="btn btn-danger ml-2">Geri</Link>        
            </Form>  );
    }
}
 
export default EditUser;