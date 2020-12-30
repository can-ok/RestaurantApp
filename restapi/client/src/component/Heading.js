import "../App.css"
import {Navbar,Nav,NavItem,NavbarBrand,Container} from 'reactstrap';
import HamburgerMenu from './HamburgerItem';

import {Button} from 'reactstrap';
import {Link} from 'react-router-dom'

import {useContext} from 'react'
import AppContext from'../AppContext';



const setAgain=(props)=>{

    /* console.log(context)
    let appState=Object.assign({},context.appState)

    context.setAppState(appState) */



}

const Heading = (props) => {


    const context=useContext(AppContext);

    return ( 

        <Navbar>
            <Container>
                <NavbarBrand  href="/" > <strong>Client </strong></NavbarBrand>
                <Link className="btn btn-primary" style={{color:'white',background:'#007bff'}}  to="/menu"><HamburgerMenu /> </Link>

            </Container>
        </Navbar>

     );
}
 
export default Heading;