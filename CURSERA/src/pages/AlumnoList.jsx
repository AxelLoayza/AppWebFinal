import { useEffect } from 'react';
import { getAlumnos, deleteAlumno } from '../api/alumnoApi';
import { useAlumnoStore } from '../store/alumnoStore';
import { Link } from 'react-router-dom';
import { toast } from 'react-toastify';
import Header from '../components/Header';

const AlumnoList = () => {
  const { alumnos, setAlumnos } = useAlumnoStore();

  useEffect(() => {
    const fetchAlumnos = async () => {
      try {
        const data = await getAlumnos();
        setAlumnos(data);
      } catch (err) {
        toast.error('Error al cargar alumnos');
      }
    };
    fetchAlumnos();
  }, [setAlumnos]);

  const handleDelete = async (id) => {
    try {
      await deleteAlumno(id);
      setAlumnos(alumnos.filter(a => a.id !== id));
      toast.success('Alumno eliminado');
    } catch (err) {
      toast.error('No se pudo eliminar el alumno');
    }
  };


  return (
    <>
      <Header />
      <main className="bg-dark text-white min-vh-100 py-5">
        <div className="container">
          <h2 className="text-center mb-4 fw-bold">Gestión de Alumnos</h2>
          <p className="text-center lead mb-5">
            Este módulo permite realizar operaciones de <strong>crear, leer, actualizar y eliminar (CRUD)</strong> registros de alumnos. Administra la información académica como nombres, apellidos, carrera y ciclo de forma rápida y segura.
          </p>

          <div className="mb-4 text-center">
            <Link to="/alumno/new" className="btn btn-fuchsia px-4 py-2 text-white">
              ➕ Nuevo Alumno
            </Link>
          </div>

          <div className="table-responsive">
            <table className="table table-bordered table-hover table-dark text-center align-middle rounded-3 overflow-hidden">
              <thead className="table-light">
                <tr>
                  <th>ID</th>
                  <th>Código</th>
                  <th>Nombres</th>
                  <th>Apellidos</th>
                  <th>Carrera</th>
                  <th>Ciclo</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {alumnos.map((alumno) => (
                  <tr key={alumno.id}>
                    <td>{alumno.id}</td>
                    <td>{alumno.codigo}</td>
                    <td>{alumno.nombre}</td>
                    <td>{alumno.apellido}</td>
                    <td>{alumno.carrera}</td>
                    <td>{alumno.ciclo}</td>
                    <td>
                      <Link
                        className="btn btn-outline-warning btn-sm me-2"
                        to={`/alumno/${alumno.id}`}
                      >
                        ✏️ Editar
                      </Link>
                      <button
                        className="btn btn-outline-danger btn-sm"
                        onClick={() => handleDelete(alumno.id)}
                      >
                        🗑️ Eliminar
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </>
  );
};

export default AlumnoList;
