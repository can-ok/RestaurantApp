
import {BrowserRouter as Router,Route,NavLink,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'

import Home from './component/Home';
import ProductDescription from './component/ProductDescription';
import AddProduct from './component/AddProduct'
import EditProduct from './component/EditProduct';

function App() {
  return (

    <div className="App_main">

    <Router>
      <Switch>
          <Route exact path="/" component={Home}/>
          <Route exact path="/description/:type/:id" component={ProductDescription} />
          <Route exact path="/add/:type" component={AddProduct}/>
          <Route exact path="/update/:type/:id" component={EditProduct} />
      </Switch>
    </Router>
    </div>
    
  );
}

export default App;
