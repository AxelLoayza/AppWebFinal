import Footer from "../components/Footer";
import Header from "../components/Header";
import { Link } from "react-router-dom";


const Home = () => {
return (
    <>
      <header>
        <Header />
      </header>

      <main className="bg-dark text-white py-5">
        <div className="container">
          {/* Hero Section */}
          <section className="text-center mb-5">
            <h1 className="display-4 fw-bold mb-3">Sistema de Control de Registros</h1>
            <p className="lead">Administra cursos y alumnos de forma eficiente, moderna y centralizada.</p>
            <Link to="/curso/new" className="btn btn-outline-light mt-3">
              Crear nuevo curso
            </Link>
          </section>

          {/* Features Section */}
          <section className="row text-center mb-5">
            <div className="col-md-4">
              <div className="p-4 border border-secondary rounded">
                <h3>📘 Gestión de Cursos</h3>
                <p>Visualiza, crea, edita o elimina cursos con facilidad.</p>
              </div>
            </div>
            <div className="col-md-4">
              <div className="p-4 border border-secondary rounded">
                <h3>👩‍🎓 Gestión de Alumnos</h3>
                <p>Registra alumnos, consulta sus datos y edita sus registros académicos.</p>
              </div>
            </div>
            <div className="col-md-4">
              <div className="p-4 border border-secondary rounded">
                <h3>🔐 Acceso Seguro</h3>
                <p>Autenticación protegida con control de roles y privilegios.</p>
              </div>
            </div>
          </section>

          {/* Estadísticas o llamada a la acción */}
          <section className="text-center mb-5">
            <h2 className="mb-3">¿Nuevo en el sistema?</h2>
            <p className="mb-4">Empieza registrando tus cursos o alumnos en segundos.</p>
            <Link to="/alumno/new" className="btn btn-fuchsia">
              Registrar Alumno
            </Link>
          </section>
        </div>
      </main>

      <Footer />
    </>
  );
};
export default Home;