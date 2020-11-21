import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import {GrFormAdd} from 'react-icons/gr'
import {Button, FormGroup, Label,Form,Input} from 'reactstrap';
import axios from 'axios';

class UserList extends Component {
    state = { 
            users:[],
            userRoleValue:""
            }


    componentDidMount(){
        //http://localhost:8080/users/getAll
        axios.get("http://localhost:8080/users/getAll")
        .then((response)=>{

            this.setState({
                users:response.data
            })

        })
        .catch((err)=>{

            console.error(err);
        })

    }


    handle_detele=(itemId)=>{
        
        const users=this.state.users.filter(item => item.id !==itemId)

        this.setState({users})
        //http://localhost:8080/users/delete/1
        axios.delete("http://localhost:8080/users/delete/"+itemId).then((response)=>console.log(response))
        .catch((err)=>console.error(err))
        
    }



    handleInputChange=(event)=>{

        const target=event.target;
        const name=target.name;
        
        this.setState({
            [name]:target.value
        })


        const value= target.value.toUpperCase();
        const users=this.state.users.filter(item=>item.role ===  value)
        if(users.length>0){
            this.setState({
                users
            })

        }

     

    }   



    render() { 

        const UserTable=this.state.users.map((user)=>{

         return( <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.name}</td>
                <td>{user.role}</td>
                <td><Link to={"/users/edit/"+user.id}  className="btn btn-warning">Edit</Link></td>
                <td><Button className="btn btn-danger" onClick={()=>this.handle_detele(user.id)}> Delete </Button></td>
            </tr>)

        })

        return ( 

            <div>

                <div className="row">


                <div className="col-md">
                <Form className="float-right">
                <FormGroup>
                <Label>
                <Input name="userRoleValue" type="text"  onChange={this.handleInputChange} />
                </Label>
                </FormGroup>

                </Form>
                </div>


                </div>
            
            <div className="row">

            <div className="mb-3 col-md">
            <strong>User List</strong> 

            <Link className="btn float-right " to="/users/add"><GrFormAdd size='1rem'/><strong>Add User</strong></Link>

            </div>

            </div>

            <div>


                <table className="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Role</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                    {UserTable}

                    </tbody>
                </table>
            </div>

            </div>
         );
    }
}

export default UserList;