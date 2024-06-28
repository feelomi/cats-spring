import { axiosAPI } from "./api/axiosClient";
import { useEffect, useState } from 'react';
import { Button, Card, Form, FormControl, InputGroup, Table } from "react-bootstrap";
const CatComponent = (props) => {
    const [cats, setCats] = useState([]);
    const [form, setForm] = useState({ name: "", age: "", breed: "", description: "" });
    const [updatingId, setUpdatingId] = useState(null);
    const formChange = (event) => {
        const { name, value } = event.target;
        setForm(prevForm => ({ ...prevForm, [name]: value }));
    };
    const createCat = (event) => {
        event.preventDefault();
        axiosAPI.post('/api/cats/', {
            ...form
        })
            .then(response => {
                setCats([...cats, response.data]);
                resetForm();
            })
            .catch(error => {

            })
    }
    const deleteCat = (id) => {
        axiosAPI.delete(`/api/cats/${id}`)
            .then(response => {
                setCats(cats.filter((cat) => cat.id !== id))
            })
            .catch(error => {

            })
    }
    const updateCat = (event) => {
        event.preventDefault();
        console.log(form)
        axiosAPI.put(`/api/cats/${updatingId}`, {...form})
            .then(response => {
                setCats(cats.map((cat) => cat.id === updatingId ? response.data : cat))
                resetForm()
                setUpdatingId(null)
            })
            .catch(error => {

            })
    }
    const resetForm = () => {
        setForm({ name: "", age: "", breed: "", description: "" });
    };
    useEffect(() => {
        axiosAPI.get('/api/cats/')
            .then(response => {
                setCats(response.data);
                console.log(response.data)
            })
            .catch(error => {
                console.log(error.response)
            })
    }, []);
    return (
        <Form onSubmit={updatingId === null ? createCat : updateCat}><Table striped bordered hover>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Breed</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                {
                    cats.map((cat) => (
                        <tr>
                            <td>{cat.id}</td>
                            <td>{cat.name}</td>
                            <td>{cat.age}</td>
                            <td>{cat.breed}</td>
                            <td>{cat.description}</td>
                            <td><Button size="sm" variant="warning" onClick={() => { setUpdatingId(cat.id); setForm(cat) }}>
                                Редагувати
                            </Button></td>
                            <td><Button size="sm" variant="danger" onClick={() => deleteCat(cat.id)}>
                                Видалити
                            </Button></td>


                        </tr>
                    ))
                }
            </tbody>
            <tfoot align="center">
                <tr>
                    <td>
                    </td>
                    <td>
                        <Form.Group className="mb-3">
                            <Form.Control required autoComplete="off" type="text" placeholder="Введіть ім'я" name="name" value={form.name} onChange={formChange} />
                        </Form.Group>
                    </td>
                    <td>
                        <Form.Group className="mb-3">
                            <Form.Control required autoComplete="off" type="number" placeholder="Введіть вік" name="age" value={form.age} onChange={formChange} />
                        </Form.Group>
                    </td>
                    <td>
                        <Form.Group className="mb-3">
                            <Form.Control required autoComplete="off" type="text" placeholder="Введіть породу" name="breed" value={form.breed} onChange={formChange} />
                        </Form.Group>
                    </td>
                    <td>
                        <Form.Group className="mb-3">
                            <Form.Control required autoComplete="off" type="text" placeholder="Введіть опис" name="description" value={form.description} onChange={formChange} />
                        </Form.Group>
                    </td>

                    {
                        updatingId === null ?
                            (<td colSpan="2">
                                <Button size="sm" variant="success" type="submit">
                                    Створити
                                </Button>
                            </td>) :
                            (<>
                                <td>
                                    <Button size="sm" variant="warning" type="submit">
                                        Оновити
                                    </Button>
                                </td>
                                <td>
                                    <Button size="sm" variant="danger" onClick={() => setUpdatingId(null)}>
                                        Назад
                                    </Button>
                                </td>
                            </>
                            )
                    }

                </tr>
            </tfoot>


        </Table></Form>
    );
}

export default CatComponent;