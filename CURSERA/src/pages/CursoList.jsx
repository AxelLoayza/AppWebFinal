import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getCursos, deleteCurso } from '../api/cursoApi';
import { toast } from 'react-toastify';
import Header from '../components/Header';

const CursoList = () => {
  const [cursos, setCursos] = useState([]);

  // Carga inicial de cursos
  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await getCursos();
        setCursos(data);
      } catch (err) {
        console.error('Error al cargar cursos:', err);
        toast.error('No se pudieron cargar los cursos');
      }
    };
    fetchData();
  }, []);

  // Eliminar un curso
  const handleDelete = async (id) => {
    if (!window.confirm('¿Eliminar este curso?')) return;
    try {
      await deleteCurso(id);
      setCursos(cursos.filter((c) => c.id !== id));
      toast.success('Curso eliminado');
    } catch (err) {
      console.error('Error al eliminar curso:', err);
      toast.error('No se pudo eliminar el curso');
    }
  };

  return (
    <>
      <Header />
      <main className="bg-dark text-white min-vh-100 py-5">
        <div className="container">
          <h1 className="text-center mb-4 fw-bold">Gestión de Cursos</h1>
          <p className="text-center lead mb-5">
            Este módulo permite realizar operaciones de <strong>crear, ver, editar y eliminar (CRUD)</strong> cursos académicos. Puedes administrar los datos de cada curso como su código, nombre y número de créditos.
          </p>

          <div className="mb-4 text-center">
            <Link to="/curso/new" className="btn btn-fuchsia px-4 py-2 text-white">
              ➕ Nuevo Curso
            </Link>
          </div>

          <div className="table-responsive">
            <table className="table table-hover table-dark text-center align-middle rounded-3 overflow-hidden">
              <thead className="table-light">
                <tr>
                  <th>ID</th>
                  <th>Código</th>
                  <th>Nombre</th>
                  <th>Créditos</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                {cursos.map((curso) => (
                  <tr key={curso.id}>
                    <td>{curso.id}</td>
                    <td>{curso.codigo}</td>
                    <td>{curso.nombre}</td>
                    <td>{curso.creditos}</td>
                    <td>
                      <Link
                        to={`/curso/${curso.id}`}
                        className="btn btn-outline-warning btn-sm me-2"
                      >
                        ✏️ Editar
                      </Link>
                      <button
                        className="btn btn-outline-danger btn-sm"
                        onClick={() => handleDelete(curso.id)}
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

export default CursoList;
