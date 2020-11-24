import {Navbar,Nav,Form,FormControl} from 'react-bootstrap'
import {Button} from 'reactstrap';
const MenuBar = (props) => {

    console.log(props.token)
    const token=props.token;
    return ( <Navbar bg="dark" variant="dark">
    <Navbar.Brand href="#home">BackOffice</Navbar.Brand>
    <Nav className="mr-auto">
      <Nav.Link href="/">Ürünler</Nav.Link>
      <Nav.Link href="/users">Kullanıcılar</Nav.Link>
      <Nav.Link href="/categories">Kategoriler</Nav.Link>
      <Nav.Link href="/tables">Masalar</Nav.Link>
      <Nav.Link href="#pricing">Raporlar</Nav.Link>
      <Nav.Link href="#pricin2g">Yetkililer</Nav.Link>
      <Nav.Link href="/config">Config</Nav.Link>

      {!token && <Nav.Link href="/login">Login</Nav.Link> }  

      {token && <Nav.Link href="/logout">LogOut</Nav.Link> }  

    </Nav>

  </Navbar> );
}
 
export default MenuBar;