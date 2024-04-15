import React, { useEffect, useState } from "react";
import { Button, Form, Row, Col } from "react-bootstrap";
import RentalAxios from "../../apis/RentalAxios";
import { useNavigate, useParams } from "react-router-dom";

const ReturnScooter = () => {

    const navigate = useNavigate()
    const scooterId = useParams().id
    const [addresses, setAddresses] = useState([])
    const [returnScooter, setReturnScooter] = useState({
        id: scooterId,
        batteryLevel: "",
        email: "",
        addressId: ""
    })

    const getAddresses = () => {
        RentalAxios.get("/addresses")
            .then(res => {
                console.log(res)
                setAddresses(res.data)
            })
            .catch(err => console.log(err))
    }

    useEffect(() => {
        getAddresses()
    }, [])

    const valueInputChanged = (e) => {
        const { name, value } = e.target;
    
        const returnScooterFromState = { ...returnScooter, [name]: value }
        setReturnScooter(returnScooterFromState);
    }

    const handleReturn = () => {

        const params = {
            ...returnScooter
        }

        RentalAxios.put(`/scooters/${scooterId}/return`, params)
         .then(res => {
            console.log(res.data)
            alert("Scooter was returned successfully!")
            navigate("/scooters")
         })
         .catch(err => {
            console.log(err)
            alert("An error has occurred while processing your request. Please try again.")
         } )

    }
    return (
        <div>
             <Row className="justify-content-center">
                <Col xs="12" sm="10" md="8">
                    <Form>
                        <Form.Group>
                            <Form.Label>Current battery level</Form.Label>
                            <Form.Control
                                type="number" name="batteryLevel" placeholder="Battery level" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Email address</Form.Label>
                            <Form.Control
                                type="text" name="email" placeholder="Email address" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Current address</Form.Label>
                            <Form.Control name="addressId" onChange={(e) => valueInputChanged(e)} as="select">
                                <option value="">Choose address</option>
                                {addresses.map((a) => {
                                    return (
                                        <option key={a.id} value={a.id}>{a.street} {a.number}</option>
                                    )
                                })}
                            </Form.Control> 
                        </Form.Group>

                        <Button variant="success" onClick={() => handleReturn()}>Return</Button>
                    </Form>
                </Col>
            </Row>

        </div>
    )
}

export default ReturnScooter