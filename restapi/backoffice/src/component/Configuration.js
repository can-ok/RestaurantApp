import React, { Component } from 'react';



class Config extends Component {
    state = { config:[] }


    componentDidMount(){
        
        var myHeaders = new Headers();
        myHeaders.append("Authorization", this.token);
 
 
        var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        };
 
        //localhost:8080/info/list
        fetch(`http://localhost:8080/info/list`,requestOptions)
        .then((response)=>{
            return response.json()
        })
        .then((data)=>{
            
            console.log(data)
            this.setState({config:data})
        })


    }


    render() {
        
        const items=this.state.config;

        const confList=(
                        <ul className="list-group">
                        <li class="list-group-item"><strong>Jpa: </strong>{items.Jpa} </li>
                        <li class="list-group-item"><strong>dataSource: </strong>{items.dataSource} </li>
                        <li class="list-group-item"><strong>dataSourceName: </strong> {items.dataSourceName}</li>
                        <li class="list-group-item"><strong>h2Console: </strong>{items.hConsole} </li>
                        <li class="list-group-item"><strong>loggingType:</strong>{items.loggingType}</li>
                        </ul>

        )

        return ( <div>
            
            {confList}
        </div>  );
    }
}
 
export default Config;