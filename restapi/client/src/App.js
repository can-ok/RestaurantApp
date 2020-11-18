import {BrowserRouter as Router,Route,NavLink,Switch} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'
import './App.css'

import Home from './component/Home';

function App() {
  return (
    <div className="App">
      
      <Router>
        <Switch>
          <Route exact path="/" component={Home}/>
        </Switch>
      </Router>

    </div>
  );
}

export default App;
