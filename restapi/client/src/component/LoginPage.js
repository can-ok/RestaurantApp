import {Form,FormGroup,Label,Input, Button} from 'reactstrap';
import React, { Component } from 'react';

import AuthService from '../api/AuthService';
import AppContext from '../AppContext';

import Switch from "react-switch";


class LoginForm extends Component {
    state = { username:"",
              password:"",
              checked:false
            }

    static contextType=AppContext;


    mySubmitHandler=()=>{
        
        AuthService.login(this.state)
        .then((response)=>{
            
            /* console.log(response)
            console.log(response.data.auth) */

            //localStorage.setItem("token","BASIC "+response.data.auth)
            let appState={...this.context.appState}
            appState.token="BASIC "+response.data.auth
            this.context.setAppState(appState)

            if(this.state.checked){
                localStorage.setItem("token",appState.token)
            }
            
           this.props.history.push("/")
            //window.location="/"; //full reload
        }).catch((err)=>{

            console.log(err)
        })

        

        //this.context.setAppState("ING")

    }        

    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })

    
    }


    handleCheck=(checked)=>{
        this.setState({ checked });
    }

    render() { 

        let appContext=this.context;
        console.log(appContext)
        
        return ( 
            <Form>
                <FormGroup>
                    <Label>Name
                    <Input name="username" type="text"  onChange={this.handleInputChange} /></Label>
                </FormGroup>
                <FormGroup>
                    <Label>Password
                    <Input name="password" type="text" onChange={this.handleInputChange}/>
                    </Label>
                </FormGroup>
                
                <h4>Remember Me <Switch title="Remember Me" onChange={this.handleCheck} checked={this.state.checked}/></h4> <br/>
                 
                 <Button  onClick={this.mySubmitHandler} className="btn btn-success">Submit</Button>


            </Form>


         );
    }
}
 
export default LoginForm;