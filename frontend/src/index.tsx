import './index.css';

import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Home from './views/Home/Home';

import { isLoggedIn } from './auth';
import Register from './views/Register/Register';
import Login from './views/Login/Login';
import RestrictedRoute from './components/RestrictedRoute/RestrictedRoute';
import User from './views/User/User';
import MyAccount from './views/MyAccount/MyAccount';


const router = createBrowserRouter([
  {
    path: '/',
    element:
        <Home/>,
    errorElement: <div>404</div>,
  },
  {
    path: '/register',
    element: <Register />,
    errorElement: <div>404</div>,
  },
  {
    path: '/login',
    element: <Login />,
    errorElement: <div>404</div>,
  },
  {
    path: '/user',
    element: <RestrictedRoute
      condition={isLoggedIn}
      component={<User />}
      invalidComponent={<Login />}
    />,
    errorElement: <div>404</div>,
  }
  ,
  {
    path: '/myaccount',
    element: <RestrictedRoute
      condition={isLoggedIn}
      component={<MyAccount />}
      invalidComponent={<Login />}
    />,
    errorElement: <div>404</div>,
  }

]);

let root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
);

