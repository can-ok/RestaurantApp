import {Navbar,Nav,Form,FormControl} from 'react-bootstrap'
import {Button} from 'reactstrap';
const MenuBar = () => {
    return ( <Navbar bg="dark" variant="dark">
    <Navbar.Brand href="#home">BackOffice</Navbar.Brand>
    <Nav className="mr-auto">
      <Nav.Link href="/">Products</Nav.Link>
      <Nav.Link href="/users">Users</Nav.Link>
      <Nav.Link href="#pricing">Reports</Nav.Link>
    </Nav>
    <Form inline>
      <FormControl type="text" placeholder="Search" className="mr-sm-2" />
      <Button variant="outline-info">Search</Button>
    </Form>
  </Navbar> );
}
 
export default MenuBar;