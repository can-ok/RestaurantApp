import {Link} from 'react-router-dom';
import "../App.css"
import {Navbar,Nav,NavItem,NavbarBrand,Container} from 'reactstrap';

import {Button} from 'reactstrap';
import HamburgerMenu from './HamburgerItem';

const Heading = (props) => {
    return ( 

        <Navbar>
            <Container>
                <NavbarBrand  href="/" > <strong>Client </strong></NavbarBrand>
                <Button style={{color:'white',background:'#007bff'}} href="/menu" onClick={() => props.func()}><HamburgerMenu /> </Button>

            </Container>
        </Navbar>

     );
}
 
export default Heading;