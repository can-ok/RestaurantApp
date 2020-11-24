import React, { Component } from 'react';
import '../App.css'

class CategoryList extends Component {
    state = { 
        category:this.props.match.params.category,
        items:[],
        status:false
     }


    componentDidMount(){

   
    const specificCategory=this.props.match.params.category


    
    fetch(`http://localhost:8080/category/${specificCategory}`)
    .then((response)=>{
        
        //this.setState({response})
        //response=response.json()
        //console.log(response)
        return response.json();
        }
    ).then((data)=>{

            

            this.setState({
                items:data,
                status:true
            })

    })
    .catch((error) => {
        console.error('Error:', error);
        });
    }



    render() {
        
        let listItem="";
        if(this.state.status)
        {
            const {items}=this.state;
            //const type="drink";
            listItem= items.map( (item)=>
    
            <div className="card">

                <div className="card-body">
                    <h5 className="card-title">{item.title}</h5>
                    <p className="card-text">{item.description}</p>
                    <p className="card-text">{item.price.toString()}</p>

                </div>

                    
    
             </div>);    
        }
       
    

        return ( <div>
            
                <h3>{this.state.category}</h3>
                {this.state.status && listItem}

        </div> );
    }
}
 
export default CategoryList;