import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import CategoryService from '../../api/CategoryService';
import TableService from '../../api/TableService';


class EditTable extends Component {
    state = {  tableCategory:[],
        selectValue:1,
        tableName:"",
        tableCount:0

    }


    componentDidMount(){

        CategoryService.getTableCategories()
        .then((response)=>{

            return response.json()
        })
        .then((data)=>{

            this.setState({
                tableCategory:data
            })
        })
    }

    mySubmitHandler=()=>{     
        
        let id=this.props.match.params.id


        console.log(this.state.tableCount)

        TableService.updateTable(id,this.state)
        
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

            <Link onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/tables" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>    );
    }
}
 
export default EditTable;