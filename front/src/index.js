import React from "react";
import { createRoot } from 'react-dom/client';
import {
    Route,
    Link,
    BrowserRouter as Router,
    Routes
} from "react-router-dom";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import { Container, Navbar, Nav, Button } from "react-bootstrap";
import Login from "./components/authorization/Login";
import { logout } from "./services/auth";
import Register from "./components/authorization/Register";
import Scooters from "./components/scooters/Scooters";
import AddScooter from "./components/scooters/AddScooter";
import CreateReservation from "./components/reservations/CreateReservation";
import ReturnScooter from "./components/reservations/ReturnScooter";




const App = () => {

    const jwt = window.localStorage['jwt'];

    if (jwt) {
        return (
            <div>
                <Router>
                    <Navbar bg="dark" variant="dark" expand>
                        <Navbar.Brand as={Link} to="/">
                            Scooter Rental
                        </Navbar.Brand>
                        <Nav className="mr-auto">
                            <Nav.Link as={Link} to="/scooters">
                                Scooters
                            </Nav.Link>
                        </Nav>
                        <Button onClick={() => logout()}>Log out</Button>

                    </Navbar>

                    <Container style={{ marginTop: 25 }}>
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/scooters" element={<Scooters/>}/>
                            <Route path="/scooters/add" element={<AddScooter/>}/>
                            <Route path="/scooters/rent/:id" element={<CreateReservation/>}/>
                            <Route path="/scooters/return/:id" element={<ReturnScooter/>}/>
                            <Route path="*" element={<NotFound />} />
                        </Routes>
                    </Container>
                </Router>
            </div>
        );
    } else {
        return (
            <div>
                <Router>
                    <Navbar bg="dark" variant="dark" expand>
                        <Navbar.Brand as={Link} to="/">
                            Scooter Rental
                        </Navbar.Brand>
                        <Nav className="mr-auto">
                            <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                            <Nav.Link as={Link} to="/register">Register</Nav.Link>
                        </Nav>
                    </Navbar>

                    <Container style={{ marginTop: 25 }}>
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/register" element={<Register />} />
                            <Route path="*" element={<NotFound />} />
                        </Routes>
                    </Container>
                </Router>
            </div>
        )
    }
}

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
    <App />,
)
