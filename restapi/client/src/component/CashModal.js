import React,{useState} from 'react';
import {Form,FormGroup,Label,Input, Button} from 'reactstrap';

const Cash = ({orderWithCash}) => {

    const [name, setName] = useState("");
    const [charge, setCharge] = useState("");

    return ( 
        <div>
        <Form className="mt-3">
        <FormGroup>
        <Label>İsim:
        <Input name="username" type="text"  onChange={(e) => setName(e.target.value) }/></Label>
        </FormGroup>
        <FormGroup>
        <Label>Tutar:
        <Input name="password" type="text"onChange={(e) => setCharge(e.target.value)} />
        </Label>
        </FormGroup>
        </Form>
             <Button onClick={()=>orderWithCash(name,charge)} className="btn btn-success">Öde</Button>
        </div>
     );
}
 
export default Cash;