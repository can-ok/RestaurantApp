import React, { useState,useContext } from 'react';
import AppContext,{ContextWrapper} from './AppContext';
import {BrowserRouter as Router,Redirect,Route,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'

import ProductList from './component/Products/ProductList';
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
import ListOrder from './component/Order/ListOrder';

import ListRoles from './component/Role/ListRole';

import Media from './component/Media/Media';

import CustomerList from './component/Customer/ListCustomer';
import AddCustomer from './component/Customer/AddCustomer';
import EditCustomer from './component/Customer/EditCustomer';


const App = () => {

   const [appState,setAppState]=useState({ 
    token:null,
    language:'en'
  
  });

  //*** */
  const context=appState.token? appState.token:localStorage.getItem('token')


  return (   <div>
      
    <MenuBar token={context}/>
    {/*className="App_main" */}
    <div className="App_main">
    <AppContext.Provider value={{appState,setAppState}}>

    <Router>
      <Switch>
      <Route exact path="/" render={(props) => {
                if (!context) return <Redirect to="/login" />;
                return <ProductList {...props} />;
              }}/>
          <Route exact path="/login" component={LoginForm}/>
          <Route exact path="/logout" component={Logout} />
          <Route exact path="/description/:type/:id" component={ProductDescription} />
          <Route exact path="/add/:type" component={AddProduct}/>
          <Route exact path="/update/:type/:id" component={EditProduct} />
          <Route exact path="/users/add" component={AddUser} />
          <Route exact path="/users" render={(props)=>{
             if (!context) return <Redirect to="/login" />;
             return <User {...props}/>;
          }}/>
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
          <Route exact path="/media" component={Media}/>
          <Route exact path="/order" component={ListOrder} />
          <Route exact path="/roles" component={ListRoles}/>
          <Route exact path="/customer" component={CustomerList}/>
          <Route exact path="/customerAdd" component={AddCustomer}/>
          <Route exact path="/customerEdit/:id" component={EditCustomer}/>
      </Switch>
    </Router>
    </AppContext.Provider>
    </div>
    </div>  );
}
 
export default App;