import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/authStore';
import { login } from '../api/auth';
import { toast } from 'react-toastify';
import {ToastContainer} from 'react-toastify';

const LoginForm = () => {
  const navigate = useNavigate();
  const setUser = useAuthStore((state) => state.setUser);

  const [credentials, setCredentials] = useState({
    username: '',
    password: ''
  });

  const handleChange = (e) => {
    setCredentials({
      ...credentials,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await login(credentials);
      setUser({ username: data.usuario, roles: data.roles });
      toast.success(`¡Bienvenido ${data.usuario}!`);
      navigate('/home');
    } catch (error) {
      toast.error('Credenciales inválidas');
    }
  };


  return (
    <>
    <div className="login-wrapper">
      <div className="login-card">
        <h2>Iniciar Sesión</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="username"
            placeholder="Usuario"
            value={credentials.username}
            onChange={handleChange}
            required
          />
          <input
            type="password"
            name="password"
            placeholder="Contraseña"
            value={credentials.password}
            onChange={handleChange}
            required
          />
          <button type="submit">Ingresar</button>
        </form>
      </div>
    </div>
    <ToastContainer/>
    </>

  );
};


export default LoginForm;
