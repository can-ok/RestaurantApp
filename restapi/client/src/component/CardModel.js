import React,{useState} from 'react';
import Cards from 'react-credit-cards';
import 'react-credit-cards/es/styles-compiled.css'
import {Form,FormGroup,Input, Button} from 'reactstrap';

const CardModel = ({orderWithCard}) => {

    const [cvc, setCvc] = useState("");
    const [expiry, setExpiry] = useState("");
    const [focus, setFocus] = useState("");

    const [name, setName] = useState("");

    const [number, setNumber] = useState("");


    return ( 
    
        <div id="PaymentForm">
        <Cards
          cvc={cvc}
          expiry={expiry}
          focused={focus}
          name={name}
          number={number}
        />
        <Form className="mt-3">
       
        <FormGroup>

        <Input
            type="tel"
            name="number"
            placeholder="Card Number"
           value={number}
           onFocus={e=>setFocus(e.target.name)}
           onChange={e=>setNumber(e.target.value)}
          />
        </FormGroup>

        <FormGroup>

        <Input
            type="tel"
            name="name"
            placeholder="Name"
           value={name}
           onFocus={e=>setFocus(e.target.name)}
           onChange={e=>setName(e.target.value)}
          />

    </FormGroup>

    <FormGroup>
        <Input
            type="tel"
            name="expiry"
            placeholder="MM/YY Expiry"
           value={expiry}
           onFocus={e=>setFocus(e.target.name)}
           onChange={e=>setExpiry(e.target.value)}
          />
    </FormGroup>

    <FormGroup>

        <Input
            type="tel"
            name="cvc"
            placeholder="CVC"
           value={cvc}
           onFocus={e=>setFocus(e.target.name)}
           onChange={e=>setCvc(e.target.value)}
          />
    </FormGroup>

        <Button onClick={()=>orderWithCard(name,number)} className="btn btn-success">Öde</Button>
        </Form>
      </div>
     );
}
 
export default CardModel;