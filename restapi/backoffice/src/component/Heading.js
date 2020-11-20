import {Link} from 'react-router-dom';
import "../App.css"
import {Navbar,Nav,NavItem,NavbarBrand,Container} from 'reactstrap';


const Heading = (props) => {
    return ( 

        <Navbar class="border-bottom border-3 mb-3" >
            <Container >
                <NavbarBrand  href={props.address}> <strong>{props.title} </strong></NavbarBrand>
                <Nav>
                   
                </Nav>
            </Container>
        </Navbar>

     );
}
 
export default Heading;