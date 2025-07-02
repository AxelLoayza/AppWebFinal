import { useState } from 'react'
import { Route, BrowserRouter, Routes} from 'react-router-dom'
import './App.css'
import Home from './pages/Home'
import CursoList from './pages/CursoList'
import AlumnoList from './pages/AlumnoList'
import AlumnoForm from './pages/AlumnoForm'
import CursoForm from './pages/CursoForm'
import LoginForm from './pages/LoginForm'
import PrivateRoute from './routes/PrivateRoute'
function App() {
  const [count, setCount] = useState(0)

  return (

    <BrowserRouter>
      <Routes>
          <Route path="/" element={<LoginForm/>} />
      
          <Route path="/home" element={<PrivateRoute><Home/> </PrivateRoute>} />
          <Route path="/curso" element={<PrivateRoute><CursoList/> </PrivateRoute>} />
          <Route path="/curso/new" element={<PrivateRoute><CursoForm/> </PrivateRoute>} />
          <Route path="/curso/:id" element={<PrivateRoute><CursoForm/> </PrivateRoute>} />
          <Route path="/alumno" element={<PrivateRoute><AlumnoList/> </PrivateRoute>} />
          <Route path="/alumno/new" element={<PrivateRoute><AlumnoForm/> </PrivateRoute>} />
          <Route path="/alumno/:id" element={<PrivateRoute><AlumnoForm/> </PrivateRoute>} />
      
      </Routes>
      
    </BrowserRouter>
  )
}

export default App
