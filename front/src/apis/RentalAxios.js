import axios from 'axios';
import { logout } from '../services/auth';
import {jwtDecode} from "jwt-decode";

var RentalAxios = axios.create({
  baseURL: 'http://localhost:8080/api',
});

RentalAxios.interceptors.request.use(
  function success(config){
    const jwt = window.localStorage['jwt'];
    if(jwt){
      const decoded = jwtDecode(jwt)
      console.log(Date.now())
      if(decoded.exp_date<Date.now()){
        alert("Istekao jwt")
        logout()
        return false
      }
      config.headers['Authorization'] = 'Bearer ' + jwt;
    }
    return config;
  }
);

RentalAxios.interceptors.response.use(
  function success(response){
      return response;
  },
  function failure(error){
    let jwt = window.localStorage['jwt'];
    if(jwt){
      if(error.response && error.response.status == 403){
        logout();
      }
    }
    
    throw error;
  }
);

export default RentalAxios;