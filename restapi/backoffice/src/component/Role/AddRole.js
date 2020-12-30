
import React from 'react';
import {Form,FormGroup,Label,Input,Button} from 'reactstrap';
import {useState} from 'react';
import {Link} from "react-router-dom";

import UserSerivce from '../../api/UserService';


export default function AddRole(){

    const [name,setName]=useState();
    const [id,setId]=useState();

    const onSubmit=(event)=>{

        UserSerivce.saveRole(id,name).then((response)=>console.log(response))
       
    }

    return(
        <Form>

        <FormGroup >
            <Label>ID:
            <Input type="text" name="id" onChange={e=>setId(e.target.value)} /></Label>
        </FormGroup>
        <FormGroup >
            <Label>İsim:
            <Input type="text" name="name" onChange={e => setName(e.target.value)}/></Label>
        </FormGroup>
        
        <Button className="btn btn-success" onClick={onSubmit}>Submit</Button>
        <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        

        </Form>

    );
}

