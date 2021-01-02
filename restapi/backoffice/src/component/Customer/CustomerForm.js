/* import React,{useState} from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {useFormik} from 'formik';

import Select from 'react-select';

const CustomerForm = ({meidaOptions,options}) => {
    const [selectedCity, setSelectedCity] = useState("");
    const [selectedMedia, setSelectedMedia] = useState("");

    const formik=useFormik({
        initialValues:{
            firstName:'',
            lastName:' ',
            phoneNumber:'',
            city:[],
            media:[],
            address:''
        },
        onSubmit:values=>{
          values.selectedCity= selectedCity[0].value,
          values.media=selectedMedia[0].value
            alert(JSON.stringify(values,null,2))
        }
    })


    return (  
        <Form onSubmit={formik.handleSubmit}> 
        <FormGroup>
         <Label>FirstName:
         <Input name="firstName" type="text"  onChange={formik.handleChange} /></Label>
       </FormGroup>
       <FormGroup>
         <Label>LastName:
         <Input name="lastName" type="text"  onChange={formik.handleChange}/></Label>
       </FormGroup>

       <FormGroup>
         <Label>PhoneNumber:
         <Input name="phoneNumber" type="text"  onChange={formik.handleChange }/></Label>
       </FormGroup>

       <FormGroup style={{width:"300px"}}>
       <Label>City: </Label>
       <Select  options={options}   onChange={selectedCity} isMulti isClearable/>
       </FormGroup>
      
       <FormGroup style={{width:"300px"}}>
       <Label>Media: </Label>
       <Select  options={meidaOptions}  onChange={selectedMedia} isMulti isClearable/>
       </FormGroup>

       <FormGroup>
         <Label>Address:
         <Input name="address" type="text"  onChange={formik.handleChange}/></Label>
       </FormGroup>

       <button type="submit" className="btn btn-success">Submit</button>
       </Form>
    );
}
 
export default CustomerForm; */