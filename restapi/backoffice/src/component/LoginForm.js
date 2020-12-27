import {Form,FormGroup,Label,Input, Button} from 'reactstrap';
import React, { Component } from 'react';

import AuthService from '../api/AuthService';
import Switch from "react-switch";

import AppContext from '../AppContext';
import ErrorAlert from './ErrorAlert';

class LoginForm extends Component {
    state = { username:"",
              password:"",
              checked:false,
              errorMessage:"",
              language:"tr"
            }

    static contextType=AppContext;

    mySubmitHandler=()=>{
        
        AuthService.login(this.state)
        .then((response)=>{

            let appState={...this.context.appState}
            appState.token="BASIC "+response.data.auth
            this.context.setAppState(appState)

            if(this.state.checked)
            {
                localStorage.setItem("token","BASIC "+response.data.auth)
                
            }

            this.props.history.push("/")
            
        }).catch(err=>{

            console.log(err.response.data.message)
            
            this.setState({
                errorMessage:err.response.data.message
            })
        
        })

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

    
    onChangeLanguage=(language)=>{

        let appState={...this.context.appState}
        appState.language=language
        this.context.setAppState(appState)

        AuthService.language=language

    }


    render() { 


        return ( 
            <div className="App container">
            <Form>
                {this.state.errorMessage===""?null:<ErrorAlert message={this.state.errorMessage}/>}

                <FormGroup>
                    <Label>Name:
                    <Input name="username" type="text"  onChange={this.handleInputChange} /></Label>
                </FormGroup>
                <FormGroup>
                    <Label>Password
                    <Input name="password" type="text" onChange={this.handleInputChange}/>
                    </Label>
                </FormGroup>
                <h4>Remember Me <Switch title="Remember Me" onChange={this.handleCheck} checked={this.state.checked}/></h4> <br/>

                <Button onClick={this.mySubmitHandler} className="btn btn-success">Submit</Button>

            </Form>
                <div className="float-right">
                <img src="https://www.countryflags.io/tr/flat/64.png" onClick={()=>this.onChangeLanguage("tr")} alt="turkey" width="50" height="50"/>
                <img src="https://www.countryflags.io/us/flat/64.png" onClick={()=>this.onChangeLanguage("en")} alt="usa" width="50" height="50"/>
                </div>
            </div>

         );
    }
}
 
export default LoginForm;