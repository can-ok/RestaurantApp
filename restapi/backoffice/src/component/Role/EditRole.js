
import React from 'react';
import {Form,FormGroup,Label,Input,Button} from 'reactstrap';
import {useState,useEffect} from 'react';
import {Link} from "react-router-dom";

import UserSerivce from '../../api/UserService';


export default function EditRole(props){

    const [name,setName]=useState();
    const [id,setId]=useState();

    const onSubmit=(event)=>{

        UserSerivce.updateRole(id,name).then((response)=>console.log(response))
       
    }


    //only run on mount
    useEffect(()=>{
        setName(props.role.name)
        setId(props.role.id)

    },[])

    return(
        <Form>

        <FormGroup >
            <Label>ID:
            <Input type="text" name="id" value={id} onChange={e=>setId(e.target.value)} /></Label>
        </FormGroup>
        <FormGroup >
            <Label>İsim:
            <Input type="text" name="name" value={name} onChange={e => setName(e.target.value)}/></Label>
        </FormGroup>
        
        <Button onClick={onSubmit} className="btn btn-warning">Düzenle</Button>
        <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        

        </Form>

    );
}

