import { Link, NavLink } from 'react-router-dom';

const Header = () => {
  const swaggerUrl = `${import.meta.env.VITE_API_BASE_URL}/swagger-ui/index.html#/`;
  return (
    <aside className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <NavLink className="navbar-brand" to="/">Sistema Alumni</NavLink>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavAltMarkup"
          aria-controls="navbarNavAltMarkup"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon" />
        </button>

        <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
          <div className="navbar-nav">
            <NavLink to="/home" className="nav-link">Home</NavLink>
            <NavLink to="/alumno" className="nav-link">Alumno</NavLink>
            <NavLink to="/curso" className="nav-link">Curso</NavLink>
            <a href={swaggerUrl} className="nav-link" aria-disabled="true">Documentación</a>
          </div>
        </div>
      </div>
    </aside>
  );
};

export default Header;
