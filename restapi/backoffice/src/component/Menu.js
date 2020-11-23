import {Navbar,Nav,Form,FormControl} from 'react-bootstrap'
import {Button} from 'reactstrap';
const MenuBar = (props) => {

    console.log(props.token)
    const token=props.token;
    return ( <Navbar bg="dark" variant="dark">
    <Navbar.Brand href="#home">BackOffice</Navbar.Brand>
    <Nav className="mr-auto">
      <Nav.Link href="/">Products</Nav.Link>
      <Nav.Link href="/users">Users</Nav.Link>
      <Nav.Link href="#pricing">Reports</Nav.Link>

      {!token && <Nav.Link href="/login">Login</Nav.Link> }  

      {token && <Nav.Link href="/logout">LogOut</Nav.Link> }  

    </Nav>

  </Navbar> );
}
 
export default MenuBar;