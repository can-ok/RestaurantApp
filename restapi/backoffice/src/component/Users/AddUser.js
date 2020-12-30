import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import Select from 'react-select';

import UserSerivce from '../../api/UserService';

import axios from 'axios';

class AddUser extends Component {
    state = { userName:"",userPass:"",selectedRole:[],authRoles:[] }


    componentDidMount(){

        UserSerivce.getAllRoles().then((respose)=>{
            
            this.setState({
                authRoles:respose.data
            })

        })

    }



    mySubmitHandler=()=>{

        UserSerivce.addUser(this.state)
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

    handleSelectChange=(items)=>{

        let list= Array.isArray(items)? items.map(item=>{
            
            //console.log(item)
            return(item.value)
        }):[]

        console.log(list)

        this.setState({
            selectedRole:list
        })

    }

    render() {

        const roles= this.state.selectedRole;

        const authOptions=this.state.authRoles.map((item)=>{
            return({'label':item.name,'value':item})

        })


       

        return ( <Form>
           
            <FormGroup>
              <Label>Name:
              <Input name="userName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Password:
              <Input name="userPass" type="password"  onChange={this.handleInputChange}/></Label>
            </FormGroup>

            <Select options={authOptions} values={authOptions.filter(obj=>roles.includes(obj.value))}  onChange={this.handleSelectChange} isMulti isClearable/>

            <FormGroup>
             

            </FormGroup>

            <Link to="/users" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/users" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> );
    }
}
 
export default AddUser;