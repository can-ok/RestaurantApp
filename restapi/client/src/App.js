import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import Home from './component/Home';

import CategoryList from './component/ProductCategory';

function App() {
  return (
    <div className="App">
      
      <Router>
        <Switch>
          <Route exact path="/" component={Home}/>
          <Route exact path="/listCategory/:category" component={CategoryList} />
        </Switch>
      </Router>

    </div>
  );
}

export default App;
