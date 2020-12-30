import React from 'react';
import '../App.css'
import loader from '../asset/loader800x800.gif'

const PageLoader = (props) => {

    if(!props.loading) return null;
    return ( <div className="loader-container">
        <div className="loader">

        <img src={loader} />

        </div>
    </div> );
}
 
export default PageLoader;