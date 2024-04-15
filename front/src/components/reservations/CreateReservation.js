import React, { useState } from "react";
import { Button, Form, Row, Col } from "react-bootstrap";
import RentalAxios from "../../apis/RentalAxios";
import { useNavigate, useParams } from "react-router-dom";

const CreateReservation = () => {

    const navigate = useNavigate()
    const scooterId = useParams().id
    const [reservation, setReservation] = useState({
        scooterId: scooterId,
        email: ""
    })

    const valueInputChanged = (e) => {
        const { name, value } = e.target;

        const reservationFromState = { ...reservation, [name]: value }
        setReservation(reservationFromState);
    }

    const handleRent = () => {
        const params = {
            ...reservation
        }

        RentalAxios.post('/reservations', params)
            .then(res => {
                console.log(res)
                alert("Scooter was rented successfully!")
                navigate("/scooters")
            })
            .catch(err => {
                console.log(err)
                alert("An error has occurred while processing your request. Please try again.")
            })
    }

    return (
        <div>
            <Row className="justify-content-center">
                <Col xs="12" sm="10" md="8">
                    <Form>
                        <Form.Group>
                            <Form.Label>Please enter your email address</Form.Label>
                            <Form.Control
                                type="text" name="email" placeholder="Email address" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>

                        <Button variant="success" onClick={() => handleRent()}>Rent</Button>
                    </Form>
                </Col>
            </Row>


        </div>
    )
}

export default CreateReservation