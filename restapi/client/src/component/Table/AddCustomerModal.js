import React from 'react';


const MODAL_STYLES={
    position:'fixed',
    top:'50%',
    left:'50%',
    transform:'translate(-50%,-50%)',
    zIndex:1001,
    backgroundColor:'#ffffff'
    
}

const OVERLAY_STYLE={
    position:'fixed',
    top:0,
    left:0,
    right:0,
    bottom:0,
    backgroundColor:'rgba(0,0,0,.7)',
    zIndex:1001
}



const CustomerAdd = () => {
    return ( <div className="CustomerModel">Customer Add</div> );
}
 
export default CustomerAdd;