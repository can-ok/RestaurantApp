import React, { Component } from 'react';

import {ExportReactCSV} from "./ExportCSV";


class Config extends Component {
    state = { config:[],
              beans:[]  
            }


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


        fetch(`http://localhost:8080/info/beans`,requestOptions)
        .then((response)=>response.json())
        .then((data)=>{
            
            this.setState({beans:data})
        })


    }


    render() {
        
        const items=this.state.config;

        const confList=(
                        <tbody>

                        <tr>
                        <td><strong>Message: </strong></td>
                        <td>{items.message}</td></tr>

                       <tr>
                       <td><strong>Jpa: </strong></td>
                        <td>{items.Jpa}</td>       </tr>

                        <tr>
                       <td><strong>dataSource: </strong></td>
                        <td>{items.dataSource}</td>       </tr>

                        <tr>
                       <td><strong>dataSourceName: </strong></td>
                        <td>{items.dataSourceName}</td>       </tr>
                        <tr>
                       <td><strong>h2Console: </strong></td>
                        <td>{items.hConsole}</td>       </tr>

                        <tr>
                       <td><strong>loggingType: </strong></td>
                        <td>{items.loggingType}</td>  </tr>

                       </tbody>



        )

        return ( <div>
             <div className="float-right mb-3">
                <ExportReactCSV  items={this.state.beans} fileName={"my-file.csv"} />
          
                 </div>

                <table className="table">
                    <thead>
                        <tr>
                        <th>Key</th>
                        <th>Value</th>
                        </tr>

                    </thead>
                    {confList}

                </table>
            
            
        </div>  );
    }
}
 
export default Config;