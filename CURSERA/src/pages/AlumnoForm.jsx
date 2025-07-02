import  { useState , useEffect } from 'react';
import { createAlumno } from '../api/alumnoApi';
import { toast } from 'react-toastify';
import Header from '../components/Header';
import { useParams } from 'react-router-dom';
import { getAlumno, updateAlumno } from '../api/alumnoApi';
import { useNavigate } from 'react-router-dom';
import Footer from '../components/Footer';
const AlumnoForm = () => {
  const navigate = useNavigate();
    const { id } = useParams();
    const esEdicion = Boolean(id);
  const [alumno, setAlumno] = useState({
    codigo: '',
    nombre: '',
    apellido: '',
    carrera: '',
    ciclo: ''
  });

  const handleChange = (e) => {
    setAlumno({ ...alumno, [e.target.name]: e.target.value });
  };


useEffect(() => {
  if (esEdicion) {
    const fetchAlumno = async () => {
      try {
        const data = await getAlumno(id);
        // ⚠️ Aseguramos que todos los campos existan con fallback ''
        setAlumno({
          codigo: data.codigo ?? '',
          nombre: data.nombre ?? '',
          apellido: data.apellido ?? '',
          carrera: data.carrera ?? '',
          ciclo: data.ciclo ?? ''
        });
      } catch (err) {
        console.error('Error al cargar alumno:', err);
      }
    };
    fetchAlumno();
  }
}, [id]);


const handleSubmit = async (e) => {
  e.preventDefault();

  try {
    if (esEdicion) {
      await updateAlumno(id, alumno);
      toast.success('Alumno actualizado correctamente');
      
    } else {
      await createAlumno(alumno);
      toast.success('Alumno registrado correctamente');
      navigate('/alumno');
      setAlumno({ codigo: '', nombre: '', apellido: '', carrera: '', ciclo: '' });
    }
    navigate('/alumno');
  } catch (error) {
    toast.error('Error al guardar alumno');

  }
};

return (
  <>
    <Header />
    <main className="bg-dark text-white min-vh-100 py-5">
      <div className="container">
        <div className="form-card p-5">
          <h2 className="text-center mb-4">{esEdicion ? 'Actualizar Alumno' : 'Registro de Alumnos'}</h2>

          <form onSubmit={handleSubmit}>
            {[
              { label: 'Código', name: 'codigo', type: 'text' },
              { label: 'Nombre', name: 'nombre', type: 'text' },
              { label: 'Apellido', name: 'apellido', type: 'text' },
              { label: 'Carrera', name: 'carrera', type: 'text' },
              { label: 'Ciclo', name: 'ciclo', type: 'number' }
            ].map(({ label, name, type }) => (
              <div className="mb-4" key={name}>
                <label className="form-label">{label}</label>
                <input
                  type={type}
                  name={name}
                  value={alumno[name]}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>
            ))}

            <div className="text-end">
              <button type="submit" className="btn btn-alert text-white">
                {esEdicion ? 'Actualizar' : 'Registrar Alumno'}
              </button>
            </div>
          </form>
        </div>
      </div>
    </main>
    <Footer/>
  </>
);

};

export default AlumnoForm;
