import React,{Component} from 'react';
import {Form,FormGroup,Label,Input} from 'reactstrap';
import {Link} from "react-router-dom";
import ProductsService from '../../api/ProductsService';
import Select from 'react-select';
import MediaService from '../../api/MediaService';

import CategoryService from '../../api/CategoryService';
import AppContext from '../../AppContext';


class AddProduct extends Component {
    state = { itemTitle:"",
              itemDescription:"",
              productCategory:"",
              price:"",
              options:[],
              mediaOptions:[],
              selectValue:[""],
              selectedMedia:[]
            }


    static contextType=AppContext;

    componentDidMount(){

      let appContext=this.context;
      let token=appContext.appState.token?appContext.appState.token:localStorage.getItem('token')

      CategoryService.token=token;

      CategoryService.getCategories()
      .then((response)=>{
          return response.json()
      })
      .then((data)=>{

        this.setState({
          options:data
        })
      })

      MediaService.getAllMedia()
      .then((response)=>response.json())
      .then((data)=>{
        this.setState({
          mediaOptions:data
        })
      })

    }


    handleInputChange=(event)=>{
      const target = event.target;
      const name = target.name;

       
        this.setState({
    
            [name]:event.target.value
        })
        
        
    }

    handleInputSelectChange=(event)=>{
      const target = event.target;
      const name = target.name;

      const options=target.options;

      var value=[];

      for (var i = 0, l =options.length; i < l; i++) {
        if (options[i].selected) {
          value.push(options[i].value);
        }
      }

      console.log(value)

      this.setState({
    
        [name]:value
    }) 


        
    }

    


    mySubmitHandler=()=>{

        const type=this.props.match.params.type;

        ProductsService.addProduct(this.state,type)
        .then((response)=>{
            console.log(response);
         })
        .catch((error) => {
            console.error('Error:', error);
          });
        
    }

    handleSelectChange=(item)=>{
        
      this.setState({
          selectedMedia:item
      })
  }


    render() { 

        const optionList=this.state.options.map((item)=>{
          return(<option key={item.id} value={[item.id,item.name]}>{item.name}</option>)
        })

        const mediaList=this.state.mediaOptions.map((option)=>{
          return({'label':<div>{option.name}  <img src={'data:image/png;base64,'+option.fileContent} width="30" /></div> ,value:option})
        })

        return ( <Form>
           
            <FormGroup>
              <Label>Title:
              <Input name="itemTitle" type="text"  onChange={this.handleInputChange} /></Label>
            </FormGroup>
            <FormGroup>
              <Label>Description:
              <Input name="itemDescription" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>

            <FormGroup>
             <select id = "dropdown" name="selectValue" multiple={true} value={this.state.selectValue}   onChange={this.handleInputSelectChange}>
                {optionList}
              </select>
              </FormGroup>
            <FormGroup>
            <Label>Media:
              
              </Label>
            <Select className="col-md-4" options={mediaList} value={this.state.selectedMedia}  onChange={this.handleSelectChange}  />
            </FormGroup>

            <FormGroup>
              <Label>Price:
              <Input name="price" type="text"  onChange={this.handleInputChange}/></Label>
            </FormGroup>


            <Link to="/" onClick={this.mySubmitHandler} className="btn btn-success">Submit</Link>
            <Link to="/" className="btn btn-danger ml-2">Cancel</Link>        
            </Form> );
    }
}
 
export default AddProduct;