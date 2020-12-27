import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import Select from 'react-select';
import CustomerService from '../../api/CustomerService';

class EditCustomer extends Component {
    state = {
        id:this.props.match.params.id,
        address:"",
        firstName:"",
        lastName:"",
        phoneNumber:"",
        selectedCity:null
     }


    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;

        this.setState({
    
            [name]:event.target.value
        })  
      }

    handleSelectChange=(item)=>{
        //console.log("selected",items)
        console.log(item[0].value)

        this.setState({
            selectedCity:item[0].value
        })
    }

    mySubmitHandler=()=>{


        CustomerService.updateCustomer(this.state)
        .then((response)=>{
            console.log(response)
        })
        .catch((error) => {
            console.error('Error:', error);
            });

    }
    
   

    render() { 

        const options = [
            { value: 'İstanbul', label: 'İstanbul' },
            { value: 'Ankara', label: 'Ankara' },
            { value: 'İzmir', label: 'İzmir' }
          ]

        return (<Form>
           
            <FormGroup>
              <Label>FirstName:
              <Input name="firstName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>lastName:
              <Input name="lastName" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>

            <FormGroup>
              <Label>phoneNumber:
              <Input name="phoneNumber" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>

            <FormGroup>
            <Label>City:</Label>
            <Select options={options}   onChange={this.handleSelectChange} isMulti isClearable/>
            </FormGroup>
           

            <FormGroup>
              <Label>Address:
              <Input name="address" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>


            <Link to="/" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/cutomer" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>  );
    }
}
 
export default EditCustomer;




