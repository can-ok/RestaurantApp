import React, { Component } from 'react';
import axios from 'axios';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";


class EditUser extends Component {
    state = {
        id:"",
        userName:"",
        userPass:"",
        userRole:"" }


    handleInputChange=(event)=>{

        const target=event.target;
        const name=event.target.name;

        this.setState({
            [name]:target.value
        })

    }    


    componentDidMount(){

        let id=this.props.match.params.id
        axios.get("http://localhost:8080/users/get/"+id)
        .then((response)=>{

            console.log(response)
            this.setState({
                id:response.data.id,
                userName:response.data.name,
                userPass:response.data.password,
                userRole:response.data.role
            })

        })
        .catch((err)=>{

            console.error(err);
        })

    }

    handleUpdate=()=>{


        let userData={
            "id":this.state.id,
            "name":this.state.userName,
            "password":this.state.userPass,
            "role":this.state.userRole
        }
        //http://localhost:8080/users/update/1
        
        axios.put(`http://localhost:8080/users/update/${this.state.id}`,userData)
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
              <Label>Name:
              <Input name="userName" type="text"  onChange={this.handleInputChange} value={userName} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Password:
              <Input name="userPass" type="text"  onChange={this.handleInputChange} value={userPass}/></Label>
            </FormGroup>
            <FormGroup>
              <Label>Role:
              <Input name="userRole" type="text"  onChange={this.handleInputChange} value={userRole}/></Label>
            </FormGroup>

            <Link to="/users" onClick={this.handleUpdate} className="btn btn-success">Submit</Link>
            <Link to="/users" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>  );
    }
}
 
export default EditUser;