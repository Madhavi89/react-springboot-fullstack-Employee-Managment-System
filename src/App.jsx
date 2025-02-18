import './App.css'
import FootherComponent from './components/FootherComponent'
import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import EmployeeComponent from './components/EmployeeComponent'

function App() {
  
  return (
    <>
    <BrowserRouter>
      <HeaderComponent />
        <Routes>
          {/* http://localshost:3000 */}          
          <Route path = '/' element = {<ListEmployeeComponent />}></Route>
          {/* http://localshost:3000/employees */}
          <Route path = '/employees' element = {<ListEmployeeComponent />}></Route>
          {/* http://localshost:3000/add-employee */}
          <Route path = '/add-employee' element = {<EmployeeComponent />}></Route>
          {/* http://localshost:3000/update-employee/1 */}
          <Route path = '/update-employee/:id' element = {<EmployeeComponent />}></Route>
        </Routes>
      <FootherComponent />   
    </BrowserRouter>
    </>
  )
}

export default App