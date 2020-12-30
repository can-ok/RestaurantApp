import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import Select from 'react-select';

import UserService from '../../api/UserService'

class EditUser extends Component {
    state = {
        id:"",
        userName:"",
        userPass:"",
        selectedRole:[],authRoles:[],        
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
                userName:response.data.username,
                userPass:response.data.password,
                enabled:response.data.enabled
            })

        })
        .catch((err)=>{

            console.error(err);
        })


        UserService.getAllRoles().then((respose)=>{
            
            this.setState({
                authRoles:respose.data
            })

        })

       
    }

    handleUpdate=()=>{

        //http://localhost:8080/users/update/1
        
        UserService.updateUser(this.state)
        .then((response)=>{
            
        
            console.log(response)
            })
        .catch((error) => {
            console.error('Error:', error);
            });

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

        const {userName,userPass,authRoles}=this.state;

        const authOptions=authRoles.map((item)=>{return({'label':item.name,'value':item})})

        return ( <Form>
           
            <FormGroup>
              <Label>Username:
              <Input name="userName" type="text"  onChange={this.handleInputChange} value={userName} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Password:
              <Input name="userPass" type="password"  onChange={this.handleInputChange} value={userPass}/></Label>
            </FormGroup>
           <FormGroup>
                <Select options={authOptions} values={authOptions.filter(obj=>roles.includes(obj.value))}onChange={this.handleSelectChange} isMulti isClearable/>
           </FormGroup>

            <Link to="/users" onClick={this.handleUpdate} className="btn btn-success">Submit</Link>
            <Link to="/users" className="btn btn-danger ml-2">Geri</Link>        
            </Form>  );
    }
}
 
export default EditUser;