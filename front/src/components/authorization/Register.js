import { useState } from "react";
import { useNavigate } from "react-router-dom";
import {Row, Col, Form, Button} from 'react-bootstrap'
import RentalAxios from "../../apis/RentalAxios";


function Register()  {

    const navigate = useNavigate();

    const user = {
        username: "",
        password: "",
        passwordConfirm: ""
    }

    const [newUser, setNewUser] = useState(user)

    const valueInputChanged = (e) => {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let userFromState = user;
        userFromState[name] = value;

        setNewUser(userFromState)
        console.log(newUser)
    }

    const register = () => {

        const params = {
            username: newUser.username,
            password: newUser.password,
            passwordConfirm: newUser.passwordConfirm
        }

        RentalAxios.post('/users', params)
         .then(res => {
            console.log(res.data)
            navigate('/login')
            alert("Your registration was successful!")

         })
         .catch(err => {
            console.log(err)
            alert("We're sorry, but we encountered an issue with your registration. Please review the information you entered and try again.")
         })
    }



    return (
        <div>
            <Row className="justify-content-center">
            <Col md={6}>
            <Form>
                <Form.Group>
                    <Form.Label>Username</Form.Label>
                    <Form.Control type="text" name="username" onChange={(e)=>valueInputChanged(e)}></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" name="password" onChange={(e)=>valueInputChanged(e)}></Form.Control>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Re-enter Password</Form.Label>
                    <Form.Control type="password" name="passwordConfirm" onChange={(e)=>valueInputChanged(e)}></Form.Control>
                </Form.Group>
            </Form>
            <Button onClick={() => register()}>Register</Button>
            </Col>
        </Row>
        </div>
    )
}

export default Register;