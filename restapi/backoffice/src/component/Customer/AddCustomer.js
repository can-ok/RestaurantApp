import React, { useState,useEffect,useContext } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {useHistory} from "react-router-dom";
import CustomerService from '../../api/CustomerService';
import Select from 'react-select';
import MediaService from '../../api/MediaService';
import AppContext from "../../AppContext";
import CustomerForm from './CustomerForm';

const AddCustomer = ({setListComponent,setAddComponent}) => {

    const [address, setAddres] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [selectedCity, setSelectedCity] = useState("");
    const [selectedMedia, setSelectedMedia] = useState("");

    const [customerMedia,setCustomerMedia]=useState([])

    const context = useContext(AppContext);
    const history=useHistory();

    useEffect(() => {
        let token = context.appState.toke
          ? context.appState.token
          : localStorage.getItem("token");
        CustomerService.token = token;
        
       
    
      }, []);


    const mySubmitHandler=(e)=>{

       var data={
        "firstName":firstName,
        "lastName":lastName,
        "phoneNumber":phoneNumber,
        "city":selectedCity[0].value,
        "address":address
        };

     
         CustomerService.addCustomer(data)
        .then((response)=>{
            
            console.log(response)
        })
        .catch((error) => {
            console.error('Error:', error);
            });  
          
        history.push("/customer")
    }


    const options = [
        { value: 'İstanbul', label: 'İstanbul' },
        { value: 'Ankara', label: 'Ankara' },
        { value: 'İzmir', label: 'İzmir' }
      ]
      
    return ( 
        <div>
            <Form> 
             <FormGroup>
              <Label>FirstName:
              <Input name="firstName" type="text"  onChange={(e) => setFirstName(e.target.value) } /></Label>
            </FormGroup>
            <FormGroup>
              <Label>LastName:
              <Input name="lastName" type="text"  onChange={(e) => setLastName(e.target.value)}/></Label>
            </FormGroup>

            <FormGroup>
              <Label>PhoneNumber:
              <Input name="phoneNumber" type="text"  onChange={(e) => setPhoneNumber(e.target.value) }/></Label>
            </FormGroup>

            <FormGroup style={{width:"300px"}}>
            <Label>City: </Label>
            <Select  options={options}   onChange={setSelectedCity} isMulti isClearable/>
            </FormGroup>
        

            <FormGroup>
              <Label>Address:
              <Input name="address" type="text"  onChange={(e) => setAddres(e.target.value) }/></Label>
            </FormGroup>


            <button onClick={(e)=>mySubmitHandler(e)} className="btn btn-success">Submit</button>
            <button onClick={()=>{setListComponent(true); setAddComponent(false);}}  className="btn btn-danger ml-2">Cancel</button>
            </Form>

        </div>
        
     );
}
 
export default AddCustomer;