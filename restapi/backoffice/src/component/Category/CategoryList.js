
import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {ListGroup,ListGroupItem,Button} from 'reactstrap';
import {GrFormAdd} from 'react-icons/gr'

import CategoryService from '../../api/CategoryService';

import AddCategory from './AddCategory';

class CategoryList extends Component {
    state = {  categories:[],
            showComponent: false,
            updateId:null
            }


    componentDidMount(){

        CategoryService.getCategories().then((respose)=>{

            return respose.json()
        }).then((data)=>{

            this.setState({
                categories:data
            })
        })

    }
    
    showForm=()=>{

        this.setState({
            showComponent:true,
            formStatus:"add"
        })
    }

    showFormUpdate=(id)=>{
        this.setState({
            showComponent:true,
            formStatus:"update",
            updateId:id
        })
    }

    handle_detele=(id)=>{

        CategoryService.deleteCategory(id).then((response)=>{
            window.location="/categories"; //full reload
            console.log(response)
        }).catch((err)=>{

            console.log(err)
        })
        
    }

    
    render() {
        
        const {categories}=this.state;


        let listTable=null;
        if(categories.length>0){
            listTable= categories.map( (item)=>
            <tr key={item.id}>
                        <td>{item.id}</td>
                        <td>{item.name}</td>
                      
    
                        <td><Button onClick={()=>this.showFormUpdate(item.id)} className="btn btn-warning">Düzenle</Button></td>
                        <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(item.id)} >Sil </Button></td>
             </tr>)
        
        }
        return ( 
        <div>
        <div className="mb-3">
        <strong>Kategori</strong> 
        <Button className="btn float-right" onClick={this.showForm}><GrFormAdd size='1rem'/><strong>Kategori Ekle</strong></Button>

        </div>
        <div>

        <table className="table">
            <thead>
                <tr>
                <th>ID</th>
                <th>İsim</th>
                <th></th>
                <th></th>
                </tr>
            </thead>
            <tbody>
                {listTable}
            </tbody>
        </table> 
        </div>
        {this.state.showComponent ? <AddCategory formStatus={this.state.formStatus} updateId={this.state.updateId} />:null}

        </div>

        );
    }
}
 
export default CategoryList;