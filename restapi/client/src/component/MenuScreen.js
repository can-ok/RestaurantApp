import {Navbar,Nav,NavItem,NavbarBrand,Container} from 'reactstrap';

import {Link} from 'react-router-dom';
import '../::/../App.css'
import React, { Component } from 'react';

class Menu extends Component {
    state = {  }
    render() { 

        const items=[{"name":"deneme"},{"name":"deneme"},{"name":"deneme"},{"name":"deneme"},{"name":"deneme"}]


        return (  <div>


                <div className="row">

                    <Link to="/" className="col-sm div1 m-2 align-middle">

                        SEPET
                    </Link>    
                    <Link className="col-sm div1 m-2">
                        MASALAR
                   </Link> 
                   <Link className="col-sm div1 m-2">
                            RAPORLAR
                   </Link>    
            
                    
                </div>   
                <div className="row">

                    <Link className="col-sm div1 m-2">
                        ÜRÜNLER
                    </Link>    
                    <Link className="col-sm div1 m-2">
                        KULLANICILAR
                    </Link>   
                    <Link className="col-sm div1 m-2">

                    </Link>   


                </div>   

                <div className="row ">

                    <Link className="col-sm div1 m-2">
                   
                    </Link>    
                    <Link className="col-sm div1 m-2">
                   
                    </Link>   
                    <Link to="/logout" className="col-sm div1 m-2">
                        ÇIKIKŞ
                    </Link>   
            
                    
                </div>    
 


            
        </div>);
    }
}
 
export default Menu;