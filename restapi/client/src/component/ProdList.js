import React, { Component } from 'react';


class ProductionList extends Component {
    state = {  items:[]  }


    componentDidMount(){
        //http://localhost:8080/drinks
        fetch("http://localhost:8080/getProducts")
        .then((response)=>{
            
            return response.json();
        }).then((data)=>{

            this.setState({
                items:data
            });
        }).catch((error)=>{

            console.error("Error :",error)
        })
    }

    render() { 

        const listItem= this.state.items.map( (item)=>
        
        <div className="d-flex mb-3 mt-2 border">

    <label>{item.title}</label>

        </div>

        );

        return ( <div>

                {listItem}

        </div>  );
    }
}
 
export default ProductionList;