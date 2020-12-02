import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import CategoryService from '../../api/CategoryService';
import { Table } from 'react-bootstrap';
import TableService from '../../api/TableService';



class AddTable extends Component {
    state = { tableName:"",
              tableCount:0,
              selectValue:1
            }



    componentDidMount(){

      /*   CategoryService.getTableCategories()
        .then((response)=>{

            return response.json()
        })
        .then((data)=>{

            this.setState({
                tableCategory:data
            })
        }) */




    }

    mySubmitHandler=()=>{     
      
        TableService.addTable(this.state).
        then((response)=>{

            window.location="/tables"
            return response.json()
        }).catch((err)=>{

            console.log(err)
        })

        
    }


    handleInputChange=(event)=>{
        const target = event.target;
        const name = target.name;
    
        this.setState({
    
            [name]:event.target.value
        })
    }

    render() { 

      
        return (<Form>
           
            <FormGroup>
              <Label>Name:
              <Input name="tableName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
            <Label>Count:
            <Input type='number' name="tableCount" /> </Label>
            </FormGroup>
            <FormGroup>
              
            </FormGroup>

            <Link to="/users" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/users" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>  );
    }
}
 
export default AddTable;