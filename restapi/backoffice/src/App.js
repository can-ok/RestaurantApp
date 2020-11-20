
import {BrowserRouter as Router,Route,NavLink,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'

import Products from './component/Products';
import ProductDescription from './component/Products/ProductDescription';
import AddProduct from './component/Products/AddProduct'
import EditProduct from './component/Products/EditProduct';

import MenuBar from './component/Menu'

import User from './component/User'
import AddUser from './component/Users/AddUser'
import EditUser from './component/Users/EditUser'

function App() {
  return (

    <div>
    
    <MenuBar/>
    <div className="App_main">

    <Router>
      <Switch>
          <Route exact path="/" component={Products}/>
          <Route exact path="/description/:type/:id" component={ProductDescription} />
          <Route exact path="/add/:type" component={AddProduct}/>
          <Route exact path="/update/:type/:id" component={EditProduct} />
          <Route  exact path="/users/add" component={AddUser} />
          <Route exact path="/users" component={User}/>
          <Route exact path="/users/edit/:id" component={EditUser} />
      </Switch>
    </Router>
    </div>
    </div>

  );
}

export default App;
