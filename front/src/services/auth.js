import { jwtDecode } from "jwt-decode"
import RentalAxios from "../apis/RentalAxios";

export const login = async function(username, password){
    const data = {
        username: username,
        password: password
    }
    try{
        const ret = await RentalAxios.post('users/auth', data);
        const decoded = jwtDecode(ret.data);
        window.localStorage.setItem('role', decoded.role.authority);
        window.localStorage.setItem('jwt', ret.data);
    }catch(error){
        console.log(error);
    }
    window.location.assign("/");
}

export const logout = function(){
    window.localStorage.removeItem('jwt');
    window.localStorage.removeItem('role');
    window.location.assign("/");
}