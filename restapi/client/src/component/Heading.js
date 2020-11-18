import {Link} from 'react-router-dom';
import "../App.css"
import {Navbar,Nav,NavItem,NavbarBrand,Container} from 'reactstrap';


const Heading = () => {
    return ( 

        <Navbar>
            <Container>
                <NavbarBrand href="/" > <strong>Client </strong></NavbarBrand>
                <Nav>
                   
                </Nav>
            </Container>
        </Navbar>

     );
}
 
export default Heading;