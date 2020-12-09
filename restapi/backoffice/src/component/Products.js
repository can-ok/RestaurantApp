import React, { Component } from 'react';
import Heading from './Heading';
import DrinkList from './Products/DrinkList';
import FoodList from './Products/FoodList';


class Products extends Component {
    state = {  }
    render() { 
        return ( 
        <div>
            <div>
                <Heading  address={"/"} title={"Products"}/> 

            </div>

            <div> 
                <DrinkList/>
              { /* <FoodList/> */}
        
            </div>
        </div> );
    }
}


export default Products;