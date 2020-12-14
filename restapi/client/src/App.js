import {BrowserRouter as Router,Redirect,Route,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import Products from './component/Products/Products'
import CategoryList from './component/Products/ProductCategory';

import Menu from './component/MenuScreen';
import Heading from './component/Heading';

import LoginForm from './component/LoginPage';

import React, { useState,useContext } from 'react';

import Logout from './component/Logout'

import TableList from './component/Table/TableList';


import AppContext,{ContextWrapper} from './AppContext';

  const App = () => {

    const [appState,setAppState]=useState(
        {
          token:null,
          table:null,
          waiter:null
        }
      );

  


    const [MenuScreenStatus, setMenuScreenStatus] = useState(false);

    const context=appState.token? appState.token:localStorage.getItem('token')
    console.log(context)

    return ( <div className="App container">
          <AppContext.Provider value={{appState,setAppState}}>

            <Router>
            <div className="border-bottom border-3" >
                  <Heading/>
            </div>

            <Switch>

          
              <Route exact path="/login" component={LoginForm}/>
                
              <Route exact path="/products"
                render={props=>{
                        if(!context) return <Redirect to="/login"/>;
                        return <Products {...props}/>
                }}
              />
              <Route exact path="/listCategory/:category" component={CategoryList} />
              <Route exact path={["/","/menu"]} 
                render={props=>{
                  if(!context) return <Redirect to="/login"/>;
                  return <Menu {...props}/>
                  }}
              />
              <Route exact path="/logout" component={Logout} />
              
              <Route exact path="/table" render={props=>{
                        if(!context) return <Redirect to="/login"/>;
                        return <TableList {...props}/>
                }} />
              
            </Switch>

            </Router>

          </AppContext.Provider>

        </div>
    );
  }
   
  
 
export default App;


