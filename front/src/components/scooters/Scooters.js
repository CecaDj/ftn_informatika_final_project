import { useCallback, useEffect, useState } from "react";
import { Form, Button, Table } from "react-bootstrap";
import RentalAxios from "../../apis/RentalAxios"
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";


const Scooters = () => {

    const navigate = useNavigate()
    const [scooters, setScooters] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPages, setTotalPages] = useState(0)
    const [addresses, setAddresses] = useState([])
    const [searchParams, setSearchParams] = useState({
        addressId: "",
        batteryLevelMin: "",
        batteryLevelMax: ""
    })

    const token = localStorage.getItem("jwt");
    const decoded = token ? jwtDecode(token) : null;
    const isAdmin = decoded?.role?.authority === "ROLE_ADMIN";
    const isUser = decoded?.role?.authority === "ROLE_USER";

    const getScooters = useCallback((page) => {

        let config = {
            params: {
                pageNo: page
            }
        };

        if (searchParams.addressId != "") {
            config.params.addressId = searchParams.addressId;
        }
        if (searchParams.batteryLevelMin != "") {
            config.params.batteryLevelMin = searchParams.batteryLevelMin;
        }
        if (searchParams.batteryLevelMax != "") {
            config.params.batteryLevelMax = searchParams.batteryLevelMax;
        }

        RentalAxios.get("/scooters", config)
            .then(res => {
                console.log(res)
                console.log(res.headers["total-pages"]);
                setScooters(res.data)
                setPageNo(page);
                setTotalPages(res.headers["total-pages"]);
            })
            .catch(err => {
                console.log(err)
                alert("An error has occurred while processing your request. Please try again.")
            })
    }, [searchParams])

    const getAddresses = () => {
        RentalAxios.get("/addresses")
            .then(res => {
                console.log(res)
                setAddresses(res.data)
            })
            .catch(err => console.log(err))
    }

    useEffect(() => {
        getScooters(0)
        getAddresses()
    }, [])

    const handleDelete = (scooterId) => {
        RentalAxios.delete("/scooters/" + scooterId)
            .then(res => {
                console.log(res)
                setScooters(prevScooters => prevScooters.filter(el => el.id !== scooterId));
                alert("Scooter was successfully deleted!")
            })
            .catch(err => {
                console.log(err)
                alert("An error has occurred while processing your request. Please try again.")
            })

    }

    const goToAdd = () => {
        navigate("/scooters/add")
    }

    const handleSearchParams = (e) => {
        const { name, value } = e.target
        const newObject = { ...searchParams, [name]: value }
        setSearchParams(newObject)

    }

    const goToRent = (scooterId) => {
        navigate("/scooters/rent/" + scooterId)
    }

    const goToReturn = (scooterId) => {
        navigate("/scooters/return/" + scooterId)
    }

    const chargeScooter = (s) => {

        s.batteryLevel = 100;

        RentalAxios.put("/scooters/" + s.id, s)
            .then(res => {
                console.log(res.data)
                alert("Scooter battery was charged successfully!")
                window.location.reload()
            })
            .catch(err => {
                console.log(err)
                alert("An error has occurred while processing your request. Please try again.")
            })

    }

    return (
        <div>

            <Form>
                <div style={{ display: "flex", flexDirection: "row" }}>
                    <div style={{ marginRight: 20, display: "flex", flexDirection: "column" }}>
                        <Form.Label>Address</Form.Label>
                        <Form.Control name="addressId" onChange={(e) => handleSearchParams(e)} value={searchParams.addressId} as="select">
                            <option value="">Choose address</option>
                            {addresses.map((a) => (
                                <option key={a.id} value={a.id}>{a.street}, {a.number}</option>
                            ))}
                        </Form.Control>
                    </div>
                    <div style={{ marginRight: 20, display: "flex", flexDirection: "column" }}>
                        <Form.Label>Battery level from</Form.Label>
                        <Form.Control type="number" name="batteryLevelMin" placeholder="Battery level from" onChange={(e) => handleSearchParams(e)} value={searchParams.batteryLevelMin} />
                    </div>
                    <div style={{ display: "flex", flexDirection: "column" }}>
                        <Form.Label>Battery level to</Form.Label>
                        <Form.Control type="number" name="batteryLevelMax" placeholder="Battery level to" onChange={(e) => handleSearchParams(e)} value={searchParams.batteryLevelMax} />
                    </div>
                    <Button style={{ marginLeft: 20, alignSelf: "flex-end" }} onClick={() => getScooters(0)}>Search</Button>
                </div>
            </Form>
            <br/>
                    

            {isAdmin ?
                [<Button style={{ marginBottom: -60 }} variant="success" onClick={() => goToAdd()}>Add Scooter</Button>]
                : null}

            <div style={{ marginTop: 25, marginBottom: 5, float: "right" }}>

                <Button variant="info"
                    style={{ width: 90 }}
                    disabled={pageNo == 0} onClick={() => getScooters(pageNo - 1)}>
                    Previous
                </Button>
                <Button variant="info"
                    style={{ width: 90, marginLeft: 5 }}
                    disabled={totalPages == 0 || pageNo == totalPages - 1} onClick={() => getScooters(pageNo + 1)}>
                    Next
                </Button>
            </div>
            <Table bordered striped style={{ marginTop: 2 }}>
                <thead className="thead-dark">
                    <tr>
                        <th>Product code</th>
                        <th>Maximum speed (km/h)</th>
                        <th>Battery level</th>
                        <th>Current address</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {scooters.map((s) => {
                        return (
                            <tr key={s.id}>
                                <td>{s.productCode}</td>
                                <td>{s.maxSpeed}km/h</td>
                                <td>{s.batteryLevel}%</td>
                                <td>{s.addressStreet} {s.addressNumber}</td>
                                <td>
                                    {isUser ?
                                    [s.rented === false && s.batteryLevel > 10 ? <Button onClick={() => goToRent(s.id)}>Rent</Button> 
                                    : null] : null}

                                    {isUser ? 
                                    [s.rented === true? <> <>Rented</> <Button variant="secondary" style={{ marginLeft: 20 }} onClick={()=> goToReturn(s.id)}>Return</Button> </> : null] : null}
                        
                                    {isAdmin ?
                                        [s.rented === false ? <><Button variant="danger" onClick={() => handleDelete(s.id) }>Delete</Button>  
                                                                <Button variant="warning" style={{ marginLeft: 10 }} onClick={() => chargeScooter(s)}>Charge battery</Button> </>
                                            : <>Rented</>]
                                        : null}</td>
                            </tr>
                        );
                    })}
                </tbody>
            </Table>

        </div>
    )
}

export default Scooters
