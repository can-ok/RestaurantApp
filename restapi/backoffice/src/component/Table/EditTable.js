import React, { Component } from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import CategoryService from '../../api/CategoryService';
import TableService from '../../api/TableService';
import MediaService from '../../api/MediaService';
import Select from 'react-select';


class EditTable extends Component {
    state = {  tableCategory:[],
        selectValue:1,
        tableName:"",
        tableCount:0,
        categoryMedia:[],
        selectedMedia:null

    }


    componentDidMount(){

    
        MediaService.getAllMedia()
        .then((response)=>{
            console.log(response)
            if(response.status===200) return response.json()
        
        })
        .then(data=>{
            this.setState({
                categoryMedia:data
            })
        })
    }

    mySubmitHandler=()=>{     
        
        let id=this.props.match.params.id


        console.log(this.state.tableCount)

        TableService.updateTable(id,this.state)
        .then((response)=>{
            if(response.status===200){
                this.props.history.push("/tables")
            }
        })
        .catch((err)=>{console.log(err)})
        
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
            <Link onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/tables" className="btn btn-danger ml-2">Cancel</Link>        
            </Form>    );
    }
}
 
export default EditTable;