import React, { Component } from 'react';
import Production from './Products';
import Heading from './Heading'
import '../App.css'

class Home extends Component {
    state = {  }
    render() { 
        return ( <div className="container">
            <div class="border-bottom border-3" >
            <Heading/>

            </div>
            <div>
            <Production/>
            </div>
        </div> );
    }
}
 
export default Home;