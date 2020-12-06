import {BrowserRouter as Router,Redirect,Route,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import Products from './component/Products/Products'
import CategoryList from './component/Products/ProductCategory';

import Menu from './component/MenuScreen';
import Heading from './component/Heading';

import LoginForm from './component/LoginPage';

import React, { Component } from 'react';

import Logout from './component/Logout'

import TableList from './component/Table/TableList';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = { 
      menuScrenStatus:false,
      token:""
   }
  }
  


   componentDidMount(){

    try{
      let token=localStorage.getItem("token");
      this.setState({token})
    }
    catch(ex){

      this.setState({token:null})
    }
   

  }

  changeScreen=()=>{

    let menuScrenStatus=this.state.menuScrenStatus? false:true;
    console.log(menuScrenStatus)
    
    this.setState({
      menuScrenStatus
    })
  }

  render() { 

    return (
      <div className="App container">
      <div className="border-bottom border-3" >
            <Heading func={this.changeScreen}/>
      
      </div>
      
        <Router>
          <Switch>
           <Route exact path="/login" component={LoginForm}/>
              
            <Route exact path="/products"
              render={props=>{
                      if(!localStorage.getItem("token")) return <Redirect to="/login"/>;
                      return <Products {...props}/>
              }}
            />
            <Route exact path="/listCategory/:category" component={CategoryList} />
            <Route exact path={["/","/menu"]} component={Menu}/>
            <Route exact path="/logout" component={Logout} />
            
            <Route exact path="/table" render={props=>{
                      if(!localStorage.getItem("token")) return <Redirect to="/login"/>;
                      return <TableList {...props}/>
              }} />
          </Switch>
        </Router>
        </div>
  
    );
  }
}
 
export default App;