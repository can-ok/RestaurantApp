
import {BrowserRouter as Router,Route,NavLink,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'

import Products from './component/Products';
import ProductDescription from './component/Products/ProductDescription';
import AddProduct from './component/Products/AddProduct'
import EditProduct from './component/Products/EditProduct';

import CategoryList from './component/Category/CategoryList';
import AddCategory from './component/Category/AddCategory';
import MenuBar from './component/Menu'

import User from './component/User'
import AddUser from './component/Users/AddUser'
import EditUser from './component/Users/EditUser'

import LoginForm from './component/LoginForm';
import Logout from './component/Logout'

import React, { Component } from 'react';

class App extends Component {
  state = { token:null }

  componentDidMount(){

    try{
      const token=localStorage.getItem("token");
      this.setState({token})
    }
    catch(ex){

      this.setState({token:null})
    }
   

  }
  render() { 
    return (

      <div>
      
      <MenuBar token={this.state.token}/>
      <div className="App_main">
  
      <Router>
        <Switch>
            <Route exact path="/" component={Products}/>
            <Route exact path="/login" component={LoginForm}/>
            <Route exact path="/logout" component={Logout} />
            <Route exact path="/description/:type/:id" component={ProductDescription} />
            <Route exact path="/add/:type" component={AddProduct}/>
            <Route exact path="/update/:type/:id" component={EditProduct} />
            <Route exact path="/users/add" component={AddUser} />
            <Route exact path="/users" component={User}/>
            <Route exact path="/users/edit/:id" component={EditUser} />
            <Route exact path="/categories" component={CategoryList} />
            <Route exact path="/addCategory" component={AddCategory}/>
        </Switch>
      </Router>
      </div>
      </div>
  
    );
  }
}
 
export default App;