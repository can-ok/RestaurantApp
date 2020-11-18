import React, { Component } from 'react';
import ProductionList from './ProdList';
import Heading from './Heading'
import '../App.css'

class Home extends Component {
    state = {  }
    render() { 
        return ( <div className="container">
            <div class="border-bottom border-3" >
            <Heading/>

            </div>
            <div className="App_main">
            <ProductionList />
            </div>
        </div> );
    }
}
 
export default Home;