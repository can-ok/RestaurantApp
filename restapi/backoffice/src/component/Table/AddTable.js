import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import CategoryService from '../../api/CategoryService';
import { Table } from 'react-bootstrap';
import TableService from '../../api/TableService';
import MediaService from '../../api/MediaService';
import Select from 'react-select';



class AddTable extends Component {
    state = { tableName:"",
              tableCount:0,
              selectValue:1,
              categoryMedia:[],
              selectedMedia:null
            }



    componentDidMount(){
       
        MediaService.getAllMedia()
        .then((response)=>{
            console.log(response)
            if(response.status==200) return response.json()
        
        })
        .then(data=>{
            this.setState({
                categoryMedia:data
            })
        })
        
           
    }

    mySubmitHandler=()=>{     
      
        TableService.addTable(this.state).
        then((response)=>{

            this.props.history.push("/tables")
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

    handleSelectChange=(item)=>{
        
        console.log(item)
        this.setState({
            selectedMedia:item
        })
    }

    render() { 
        const categoryOptions=this.state.categoryMedia.map((item)=>{
            return({label:<div>{item.name} <img src={'data:image/png;base64,'+item.fileContent} width="30" /> </div> ,value:item}
                )
        })
      
        return (<Form>
           
            <FormGroup>
              <Label>Name:
              <Input name="tableName" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
            <Label>Count:
            <Input type='number' name="tableCount" /> </Label>
            </FormGroup>
            <FormGroup style={{width: '300px'}}>
            <Label>Media:</Label>
            <Select  options={categoryOptions}  value={this.state.selectedMedia} onChange={this.handleSelectChange} />
            </FormGroup>

            <Link to="/users" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/users" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>  );
    }
}
 
export default AddTable;