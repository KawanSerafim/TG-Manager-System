import { Navbar, Nav, Container, NavDropdown } from 'react-bootstrap';

function AppNavBar() {
  return(
// 2. <nav> vira <Navbar>
    // - classes de cor/tema (bg-dark, navbar-dark) viram "props" (bg="dark", variant="dark")
    // - classe de expansão (navbar-expand-lg) vira "prop" (expand="lg")
    // - Sua classe customizada "navbar-custom" é passada como "className"
    <Navbar bg="dark" variant="dark" expand="lg" className="navbar-custom">
      
      {/* 3. <div class="container-fluid"> vira <Container fluid> */}
      <Container fluid>
        
        {/* 4. <a class="navbar-brand"> vira <Navbar.Brand> */}
        <Navbar.Brand href="#home">Menu</Navbar.Brand>
        
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
            <Nav.Link href="../landing/index.html" active>Home</Nav.Link>
            <Nav.Link href="../login/login.html">Login</Nav.Link>
            <Nav.Link href="../professor/send_file_student_group.html">Enviar Arquivo de Turma de TG</Nav.Link>
            
            {/* 8. <li class="nav-item dropdown"> vira <NavDropdown> */}
            {/* O texto do link ("Cadastros") vira a "prop" title */}
            <NavDropdown title="Cadastros" id="basic-nav-dropdown">
              
              {/* 9. <a class="dropdown-item"> vira <NavDropdown.Item> */}
              <NavDropdown.Item href="../create/create_professor.html">Cadastrar Professor</NavDropdown.Item>
              <NavDropdown.Item href="../create/create_course.html">Cadastrar Cursos</NavDropdown.Item>
              <NavDropdown.Item href="../create/create_student.html">Finalizar Cadastro Aluno</NavDropdown.Item>
            </NavDropdown>

          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}
export default AppNavBar;