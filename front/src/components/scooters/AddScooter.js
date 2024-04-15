import React, { useEffect, useState } from "react";
import { Button, Form, Col, Row } from "react-bootstrap";
import RentalAxios from "../../apis/RentalAxios";
import { useNavigate } from "react-router-dom";

const AddScooter = () => {

    const navigate = useNavigate()
    const [newScooter, setNewScooter] = useState({
        productCode: "",
        maxSpeed: "",
        addressId: ""
    })
    const [addresses, setAddresses] = useState([])

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
    
        const scooterFromState = { ...newScooter, [name]: value }
        setNewScooter(scooterFromState);
    }

    const handleAdd = () => {
        const params = {
            ...newScooter
        }

        RentalAxios.post('/scooters', params)
            .then(res => {
                console.log(res)
                alert("Scooter was successfully added!")
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
                            <Form.Label>Product code</Form.Label>
                            <Form.Control
                                type="text" name="productCode" placeholder="Product code" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Maximum speed</Form.Label>
                            <Form.Control
                                type="number" name="maxSpeed" placeholder="Maximum speed (km/h)" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Address</Form.Label>
                            <Form.Control name="addressId" onChange={(e) => valueInputChanged(e)} as="select">
                                <option value="">Choose address</option>
                                {addresses.map((a) => {
                                    return (
                                        <option key={a.id} value={a.id}>{a.street} {a.number}</option>
                                    )
                                })}
                            </Form.Control> 
                        </Form.Group>

                        <Button style={{ marginTop:10 }} onClick={() => handleAdd()}>Add scooter</Button> 

                        <br />
                    </Form>
                    </Col>
                    </Row>

        </div>
    )
}

export default AddScooter