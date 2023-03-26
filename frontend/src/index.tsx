import './index.css';

import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Home from './views/Home/Home';

import { isAdmin, isLoggedIn, updateUserState } from './auth';
import Register from './views/Register/Register';
import Login from './views/Login/Login';


const router = createBrowserRouter([
  {
    path: '/',
    element: <Home />,
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
  }

]);

let root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
);

