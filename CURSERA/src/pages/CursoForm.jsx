// src/pages/CursoForm.jsx

import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import Header from '../components/Header';
import {
  createCurso,
  getCurso,       // Asegúrate de exportar este método en cursoApi.js
  updateCurso
} from '../api/cursoApi';

import Footer from '../components/Footer';
const CursoForm = () => {
  const { id } = useParams();
  const esEdicion = Boolean(id);
  const navigate = useNavigate();

  // Estado del formulario y errores
  const [curso, setCurso] = useState({ nombre: '', creditos: '' });
  const [errors, setErrors] = useState({});

  // Precarga datos en modo edición
  useEffect(() => {
    if (!esEdicion) return;

    const fetchCurso = async () => {
      try {
        const data = await getCurso(id);
        setCurso({
          nombre: data.nombre ?? '',
          creditos: data.creditos ?? ''
        });
      } catch (err) {
        console.error('Error al cargar curso:', err);
        toast.error('No se pudo cargar el curso');
      }
    };

    fetchCurso();
  }, [esEdicion, id]);

  // Manejo de cambios en inputs
  const handleChange = (e) => {
    const { name, value } = e.target;
    setCurso((prev) => ({ ...prev, [name]: value }));
  };

  // Envío de formulario
  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrors({});

    try {
      if (esEdicion) {
        await updateCurso(id, curso);
        toast.success('Curso actualizado correctamente');
      } else {
        await createCurso(curso);
        toast.success('Curso creado correctamente');
      }
      navigate('/curso');
    } catch (err) {
      // Si el backend devuelve un objeto { errors: { field: message } }
      if (err.response?.data?.errors) {
        setErrors(err.response.data.errors);
      } else {
        console.error('Error al guardar curso:', err);
        toast.error('Hubo un problema al guardar');
      }
    }
  };
return (
  <>
    <Header />
    <main className="bg-dark text-white min-vh-100 py-5">
      <div className="container">
        <div className="form-card p-5">
          <h2 className="text-center mb-4">{esEdicion ? 'Actualizar Curso' : 'Crear Curso'}</h2>

          {Object.keys(errors).length > 0 && (
            <ul className="alert alert-danger">
              {Object.entries(errors).map(([field, msg]) => (
                <li key={field}>{msg}</li>
              ))}
            </ul>
          )}

          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="form-label">Nombre</label>
              <input
                type="text"
                name="nombre"
                className={`form-control ${errors.nombre ? 'is-invalid' : ''}`}
                value={curso.nombre}
                onChange={handleChange}
                required
              />
              {errors.nombre && (
                <div className="invalid-feedback">{errors.nombre}</div>
              )}
            </div>

            <div className="mb-4">
              <label className="form-label">Créditos</label>
              <input
                type="number"
                name="creditos"
                className={`form-control ${errors.creditos ? 'is-invalid' : ''}`}
                value={curso.creditos}
                onChange={handleChange}
                required
              />
              {errors.creditos && (
                <div className="invalid-feedback">{errors.creditos}</div>
              )}
            </div>

            <div className="text-end">
              <button type="submit" className="btn btn-alert text-white">
                {esEdicion ? 'Actualizar Curso' : 'Crear Curso'}
              </button>
            </div>
          </form>
        </div>
      </div>
    </main>
    <Footer />
  </>
);

};

export default CursoForm;
