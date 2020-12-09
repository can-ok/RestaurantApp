
import {Link} from 'react-router-dom';
import '../::/../App.css'
import React, { Component } from 'react';
import AppContext from '../AppContext';

class Menu extends Component {
    state = {  }
    static contextType=AppContext;

    render() { 

        let appContext=this.context;
        let tokenContext=appContext.appState.token;
        console.log(appContext)
        return (  <div>


                <div className="row">

                    <Link to="/products" className="col-sm div1 m-2 align-middle">
                        SEPET
                    </Link>    
                    <Link to="/table" className="col-sm div1 m-2">
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

               { tokenContext&& <div className="row ">

                    <Link className="col-sm div1 m-2">
                   
                    </Link>    
                    <Link className="col-sm div1 m-2">
                   
                    </Link>   
                    <Link to="/logout" className="col-sm div1 m-2">
                        ÇIKIKŞ
                    </Link>   

                    
        </div> }

        { !tokenContext && <div className="row ">

                    <Link className="col-sm div1 m-2">
                   
                    </Link>    
                    <Link className="col-sm div1 m-2">
                   
                    </Link>   
                    <Link to="/login" className="col-sm div1 m-2">
                        GİRİŞ
                    </Link>   

                    
        </div> }
 


            
        </div>);
    }
}
 
export default Menu;