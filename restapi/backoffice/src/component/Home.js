import React, { Component } from 'react';
import Heading from './Heading';
import DrinkList from './DrinkList';
import FoodList from './FoodList';

class Home extends Component {
    state = {  }
    render() { 
        return ( 
        <div>
        <Heading/> 

            <div className="row">
                <div className="col">
                <DrinkList/>
                </div>

                <div className="col">
                <FoodList/>
                </div>

            </div>
        </div> );
    }
}


export 

default Home;