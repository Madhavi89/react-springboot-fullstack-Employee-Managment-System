import { useState, useEffect } from "react";
import { useNavigate, useParams} from "react-router-dom";
import { addEmployee, getEmployee, updateEmployee } from "../services/EmployeeService";

const EmployeeComponent = () => {
    const[firstName, setFirstName] = useState("")
    const[lastName, setLastName] = useState("")
    const[email, setEmail] = useState("")

    const[errors, setErrors] = useState({
        firstName:"",
        lastName:"",
        email:""
    })
    const {id} = useParams();
    const navigator = useNavigate();

    useEffect(()=>{
        if(id){
            getEmployee(id).then((response)=>{
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
            }).catch(error =>{
                console.error(error)
            })
        }
    }, [id])

    function saveOrUpdateEmployee(e){
        e.preventDefault();
        if (validateForm()){    
            const employee = {firstName, lastName, email}
            console.log(employee);

            if(id){
                updateEmployee(id, employee).then((response) => {
                    console.log(response.data);
                    navigator('/employees');
                }).catch(error =>{
                    console.error(error);
                })
            }else{               

                addEmployee(employee).then((Response) => {
                    console.log(Response.data);
                    navigator('/employees');
                }).catch(error =>{
                    console.error(error);
                })
                
            }    
        }
    }

    function validateForm(){
        let isValid = true;

        const errorsCopy = {...errors}

        if (!firstName.trim()) {
            errorsCopy.firstName = "First Name is required";
            isValid = false;
        }
        if (!lastName.trim()) {
            errorsCopy.lastName = "Last Name is required";
            isValid = false;
        }
        if (!email.trim()) {
            errorsCopy.email = "Email is required";
            isValid = false;
        } else if (!/\S+@\S+\.\S+/.test(email)) {
            errorsCopy.email = "Invalid email format";
            isValid = false;
        }

        setErrors(errorsCopy);
        return isValid;
    }

    function pageTitle(){
        if (id) {
            return <h2 className="text-center">Update Employee</h2>        
        }else {
            return <h2 className="text-center">Add Employee</h2>
        }
    }

  return (
    <div className="container">
        <br/><br/>
        <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
                {pageTitle()}
                 <div className="card-body">
                    <form>
                        <div className="form-group mb-2">
                            <label className="form-label">First Name</label>
                            <input
                                type = "text"
                                placeholder = "Enter employee First Name"
                                name = "firstName"
                                value = {firstName}
                                className ={`form-control ${errors.firstName ? "is-invalid" : ""}`}
                                onChange={(e) => setFirstName(e.target.value)}
                            >
                            </input>
                            {errors.firstName && <div className="invalid-feedback">{errors.firstName}</div>}
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Last Name</label>
                            <input
                                type = "text"
                                placeholder = "Enter employee Last Name"
                                name = "lastName"
                                value = {lastName}
                                className={`form-control ${errors.lastName ? "is-invalid" : ""}`}
                                onChange={(e) => setLastName(e.target.value)}
                            >
                            </input>
                            {errors.lastName && <div className="invalid-feedback">{errors.lastName}</div>}
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Email</label>
                            <input
                                type = "text"
                                placeholder = "Enter employee Email"
                                name = "email"
                                value = {email}
                                className={`form-control ${errors.email ? "is-invalid" : ""}`}
                                onChange={(e) => setEmail(e.target.value)}
                            >
                            </input>
                            {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                        </div>
                        <button  className="btn btn-success" onClick={saveOrUpdateEmployee}>Submit</button>
                    </form>
                 </div>
            </div>
        </div>
    </div>
  );
}

export default EmployeeComponent;
