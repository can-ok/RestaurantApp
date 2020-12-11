
import {BrowserRouter as Router,Route,NavLink,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import React, { Component } from 'react';

import Products from './component/Products';
import ProductDescription from './component/Products/ProductDescription';
import AddProduct from './component/Products/AddProduct'
import EditProduct from './component/Products/EditProduct';

import CategoryList from './component/Category/CategoryList';
import AddCategory from './component/Category/AddCategory';
import EditCategory from './component/Category/EditCategory';

import MenuBar from './component/Menu'

import User from './component/User'
import AddUser from './component/Users/AddUser'
import EditUser from './component/Users/EditUser'

import LoginForm from './component/LoginForm';
import Logout from './component/Logout'

import Config from './component/Configuration';

import TableList from './component/Table/TableList';

import AddTable from './component/Table/AddTable';
import EditTable from './component/Table/EditTable';

import WaiterList from './component/Waiter/WaiterList';
import AddWaiter from './component/Waiter/AddWaiter';
import EditWaiter from './component/Waiter/EditWaiter';
import OrderList from './component/Order/OrderList';

import Media from './component/Media/Media';

import AppContext from './AppContext';

class App extends Component {
  state = { token:null ,

    
        }

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
      {/*className="App_main" */}
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
            <Route exact path="/editCategory/:id" component={EditCategory}/>
            <Route exact path="/config" component={Config} />
            <Route exact path="/tables" component={TableList} />
            <Route exact path="/addTable" component={AddTable} />
            <Route exact path="/editTable/:id" component={EditTable} />
            <Route exact path="/waiters" component={WaiterList}/>
            <Route exact path="/addWaiter" component={AddWaiter}/>
            <Route exact path="/editWaiter/:id" component={EditWaiter}/>
            <Route exact path="/media/" component={Media}/>
            <Route exact path="/order" component={OrderList} />
        </Switch>
      </Router>
      </div>
      </div>  
    );
  }
}
 
export default App;