import React,{Component} from 'react'

const AppContext=React.createContext()



/* export class ContextWrapper extends React.Component {
	constructor() {
	    super();
	    this.state = {
        token:"",
		actions: {
			addtoken: (token) => this.setState({token:token })
		}
	    };
	}
	render() {
		return (
		<AppContext.Provider value={this.state}>
	        	{this.props.children}
		</AppContext.Provider>
		);
	}
}
 */
export default AppContext;