import  { Component } from 'react';
import UserSerivce from '../../api/UserService'
import {ListGroup,ListGroupItem,Button} from 'reactstrap';
import {GrFormAdd} from 'react-icons/gr'
import {Link} from 'react-router-dom';

import EditRole from './EditRole';
import AddRole from './AddRole';
import CategoryService from '../../api/CategoryService';
import PageLoader from '../PageLoader';

class ListRoles extends Component {
    state = { roles:[],
              editStatus:false,
              addStatus:false,
              selectedItem:null,
              loading:true

               }

    componentDidMount(){

        UserSerivce.getAllRoles()
        .then((response)=>{

            this.setState({

                roles:response.data,
                loading:false

            })
        })

    }

    render() { 

        let rolesList=this.state.roles.map((role)=>
            <tr id={role.id}>
                <td>{role.id}</td>
                <td>{role.name}</td>
                <td><Button onClick={(e)=>this.setState({editStatus:true,selectedItem:role})}   className="btn btn-warning">Düzenle</Button></td>
                <td><Button className="btn btn-danger" >Sil </Button></td>
            </tr>

        )
        let {editStatus,addStatus}=this.state;
        
        if(!editStatus&&!addStatus){
            return ( 
            
                <div>
                <PageLoader loading={this.state.loading} />

               <div className="mb-3">
               <strong>Role</strong> 
               <a className="btn float-right" onClick={(e)=>this.setState({addStatus:true})} ><GrFormAdd size='1rem'/><strong>Role Ekle</strong></a>
       
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
                       {rolesList}
                   </tbody>
               </table> 
               </div>
               </div> );
        }
        if(editStatus){
            return ( <EditRole role={this.state.selectedItem}/>)
            
        }

        else if(addStatus){
            return ( <AddRole/>)
        }
        
    }
}
 
export default ListRoles;