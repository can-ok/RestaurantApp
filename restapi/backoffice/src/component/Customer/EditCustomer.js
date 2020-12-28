import React, { useState,useEffect,useContext } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {useHistory} from "react-router-dom";
import CustomerService from '../../api/CustomerService';
import Select from 'react-select';
import MediaService from '../../api/MediaService';
import AppContext from "../../AppContext";


const EditCustomer = ({selectedItem,setListComponent,setAddComponent}) => {
    
    const [custumerId, setCustomerId] = useState(selectedItem.id);
    const [address, setAddres] = useState(selectedItem.address);
    const [firstName, setFirstName] = useState(selectedItem.firstName);
    const [lastName, setLastName] = useState(selectedItem.lastName);
    const [phoneNumber, setPhoneNumber] = useState(selectedItem.phoneNumber);
    const [selectedCity, setSelectedCity] = useState(selectedItem.selectedCity);
    const [selectedMedia, setSelectedMedia] = useState(selectedItem.media);

    const [customerMedia,setCustomerMedia]=useState([])

    const context = useContext(AppContext);
    const history=useHistory();

    useEffect(() => {


        let token = context.appState.toke
          ? context.appState.token
          : localStorage.getItem("token");
        CustomerService.token = token;
        
        MediaService.getAllMedia()
        .then((response)=>{
            console.log(response)
            if(response.status===200) return response.json()
        
        })
        .then(data=>{
          setCustomerMedia(data)
        })



    
      }, []);


    const mySubmitHandler=(e)=>{

     var data={
        "id":custumerId,
        "firstName":firstName,
        "lastName":lastName,
        "phoneNumber":phoneNumber,
        "city":selectedCity[0].value,
        "address":address,
        "media":selectedMedia[0].value
        };

    
        CustomerService.updateCustomer(data)
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
    const meidaOptions=customerMedia.map((item)=>{
        return({label:<div>{item.name} <img src={'data:image/png;base64,'+item.fileContent} width="30" /> </div> ,value:item}
            )
    })

    return (  <div>
        <Form> 
         <FormGroup>
          <Label>FirstName:
          <Input name="firstName" type="text"  value={firstName} onChange={(e) => setFirstName(e.target.value) } /></Label>
        </FormGroup>
        <FormGroup>
          <Label>LastName:
          <Input name="lastName" type="text"  value={lastName}  onChange={(e) => setLastName(e.target.value)}/></Label>
        </FormGroup>

        <FormGroup>
          <Label>PhoneNumber:
          <Input name="phoneNumber" type="text"  value={phoneNumber}  onChange={(e) => setPhoneNumber(e.target.value) }/></Label>
        </FormGroup>

        <FormGroup style={{width:"300px"}}>
        <Label>City: </Label>
        <Select  options={options} value={selectedCity}    onChange={setSelectedCity} isMulti isClearable/>
        </FormGroup>
       
        <FormGroup style={{width:"300px"}}>
        <Label>Media: </Label>
        <Select  options={meidaOptions}  onChange={setSelectedMedia} isMulti isClearable/>
        </FormGroup>

        <FormGroup>
          <Label>Address:
          <Input name="address" type="text"  value={address} onChange={(e) => setAddres(e.target.value) }/></Label>
        </FormGroup>


        <button onClick={(e)=>mySubmitHandler(e)} className="btn btn-success">Submit</button>
        <button onClick={()=>{setListComponent(true); setAddComponent(false);}}  className="btn btn-danger ml-2">Cancel</button>
        </Form>
    </div> );
}
 
export default EditCustomer;