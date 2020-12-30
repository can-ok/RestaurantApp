import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import Select from 'react-select';

import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

import WaiterService from '../../api/WaiterService';
import AppContext from '../../AppContext';

class AddWaiter extends Component {
    state = { waiterFirstName:"",
              waiterLastName:"",
              waiterEmail:"", 
              selectedMedia:[],
              categoryMedia:[],
              selectedDate:""
             }
    
    static contextType=AppContext;

    componentDidMount(){
        let appContext=this.context;
        let token=appContext.appState.token?appContext.appState.token:localStorage.getItem('token')

        WaiterService.token=token;

        fetch("http://localhost:8080/media/getAll",{
            method:'GET'
        }).then((response)=>response.json())
        .then((data)=>{
            this.setState({
                categoryMedia:data
            })
        })
        .catch((err)=>console.log(err))

    }

    mySubmitHandler=()=>{     
      
        WaiterService.addWaiter(this.state).
        then((response)=>{

            //window.location="/waiters"
           console.log(response)

            this.props.history.push("/waiters")

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
    handleSelectChange=(item)=>{
        
        console.log(item)
        this.setState({
            selectedMedia:item
        })
    }

    handleDateSelect=(date)=>{
        console.log(date)

        this.setState({
            selectedDate:date
        })

    }


    render() { 

        const categoryOptions=this.state.categoryMedia.map((item)=>{
            return({label:<div>{item.name} <img src={'data:image/png;base64,'+item.fileContent} width="30" /> </div> ,value:item}
                )
        })

        return ( <Form>
           
            <FormGroup>
              <Label>Name:
              <Input name="waiterFirstName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>LastName:
              <Input name="waiterLastName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Emai:
              <Input name="waiterEmail" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Birth:
              </Label>
              <DatePicker
                selected={this.state.selectedDate}
        
                onChange={this.handleDateSelect} //only when value has changed
            />
            </FormGroup>
            <FormGroup style={{width: '300px'}}>
              <Label>Media:</Label>
              <Select  options={categoryOptions}  value={this.state.selectedMedia} onChange={this.handleSelectChange} />

            </FormGroup>
            
              

            <Link to="/waiters" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/waiters" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> );
    }
}
 
export default AddWaiter;