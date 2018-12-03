import React from 'react';
import ReactDOM from 'react-dom';

import * as serviceWorker from './serviceWorker';

import {ApolloProvider} from 'react-apollo';
import { ApolloClient } from 'apollo-client';
import { HttpLink } from 'apollo-link-http';

import './index.css';
import App from './App';


//ReactDOM.render(<App />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();

const client = new ApolloClient(
{
	link: new HttpLink({url: 'http://localhost:8080/graphql'})
})

ReactDOM.render(
	<ApolloProvider client={client}>
	<App/>
	</ApolloProvider>,
	document.getElementById('root')
);
