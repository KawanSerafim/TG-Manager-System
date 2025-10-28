import { Navbar, Nav, Container, NavDropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';

function NavBarApp() {
  return (
    // 2. <nav> vira <Navbar>
    // - classes de cor/tema (bg-dark, navbar-dark) viram "props" (bg="dark", variant="dark")
    // - classe de expansão (navbar-expand-lg) vira "prop" (expand="lg")
    // - Sua classe customizada "navbar-custom" é passada como "className"
    <Navbar bg="dark" variant="dark" expand="lg" className="navbar-custom">

      {/* 3. <div class="container-fluid"> vira <Container fluid> */}
      <Container fluid>

        {/* 4. <a class="navbar-brand"> vira <Navbar.Brand> */}
        <Navbar.Brand as={Link} to="/">Menu</Navbar.Brand>

        {/* 5. O <button> e o <div> do "hambúrguer" são simplificados */}
        {/* O <Navbar.Toggle> cuida do clique... */}
        <Navbar.Toggle aria-controls="navbarNavDropdown" />

        {/* ...e o <Navbar.Collapse> cuida da exibição */}
        <Navbar.Collapse id="navbarNavDropdown">

          {/* 6. <ul class="navbar-nav"> vira <Nav> */}
          {/* "me-auto" (margin-end: auto) é o padrão para alinhar à esquerda */}
          <Nav className="me-auto">

            {/* 7. <li class="nav-item"><a class="nav-link"> vira só <Nav.Link> */}
            {/* A classe "active" vira uma "prop" (active) */}
            <Nav.Link as={Link} to="/" active>Home</Nav.Link>
            <Nav.Link as={Link} to="/login">Login</Nav.Link>
            <Nav.Link  as={Link} to="/professor/enviar-arquivo">Enviar Arquivo de Turma de TG</Nav.Link>

            {/* 8. <li class="nav-item dropdown"> vira <NavDropdown> */}
            {/* O texto do link ("Cadastros") vira a "prop" title */}
            <NavDropdown title="Cadastros" id="basic-nav-dropdown">

              {/* 9. <a class="dropdown-item"> vira <NavDropdown.Item> */}
              <NavDropdown.Item as={Link} to="/cadastro/professor">Cadastrar Professor</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/cadastro/curso">Cadastrar Cursos</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/cadastro/aluno">Finalizar Cadastro Aluno</NavDropdown.Item>
            </NavDropdown>

          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
export default NavBarApp;