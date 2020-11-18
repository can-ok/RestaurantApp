import React, { Component } from 'react';
import Heading from './Heading';
import DrinkList from './DrinkList';
import FoodList from './FoodList';

class Home extends Component {
    state = {  }
    render() { 
        return ( 
        <div>

            <div class="border-bottom border-3 mb-2">
                <Heading/> 

            </div>

            <div> 
                <DrinkList/>
                <FoodList/>
        
            </div>
        </div> );
    }
}


export 

default Home;