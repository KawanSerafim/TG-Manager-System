import React, { useState, useEffect } from 'react';
import {
  Container,
  Row,
  Col,
  Card,
  Form,
  Button,
  Table,
  Toast,
  ToastContainer
} from 'react-bootstrap';

function CreateCourse() {
  // JS
  //Estados para os campos dos formularios
  const [courseName, setCourseName] = useState('');
  const [shift, setShift] = useState('');
  const [tgCoordinatorReg, setTgCoordinatorReg] = useState('');
  const [courseCoordinatorReg, setCourseCoordinatorReg] = useState('');

  // Estado para a lista de cursos (a tabela)
  const [courses, setCourses] = useState([]); // Começa como um array vazio

  // Estado para validação do formulário
  const [validated, setValidated] = useState(false);

  // Estado para os Toasts (notificações)
  const [showSuccessToast, setShowSuccessToast] = useState(false);
  const [showErrorToast, setShowErrorToast] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');


  // Efeitos colaterais (ao montar o componente faça ...)
const fetchCourses = async () => {
    try {
      // TODO: Atualize esta URL para a rota correta da sua API Spring
      const response = await fetch('http://localhost:8080/api/courses');
      if (!response.ok) {
        throw new Error('Falha ao buscar cursos');
      }
      const data = await response.json();
      setCourses(data); // 4. Coloca os cursos no estado, e o React atualiza a tabela
    } catch (error) {
      setErrorMessage(error.message);
      setShowErrorToast(true);
    }
  };

    useEffect(() => {
      // Chama a função de busca
      fetchCourses();
      // Executa apenas ao montar
    }, []);

    // Handlers
    const handleSubmit = async (event) => {
      //Para o envio do forms
      event.preventDefault();

      const form = event.currentTarget;
      // Checa a validação do React-Bootstrap
      if (form.checkValidity() === false) {
        event.stopPropagation();
        setValidated(true);
        // Para a execução se o formulário for inválido
        return;
      }
      setValidated(true);

      // 5. Monta o objeto de dados para enviar para a API
      const courseData = {
        name: courseName,
        shift: shift,
        tgCoordinatorRegistration: tgCoordinatorReg,
        courseCoordinatorRegistration: courseCoordinatorReg
      };

      try {
        // TODO: Atualize esta URL para a rota POST correta da sua API Spring
        const response = await fetch('http://localhost:8080/api/courses', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(courseData),
        });

        if (!response.ok) {
          // Tenta ler a mensagem de erro da API
          const errorData = await response.json();
          throw new Error(errorData.message || 'Erro ao cadastrar curso');
        }

        // Se deu tudo certo (response.ok)
        setShowSuccessToast(true); // Mostra o toast de sucesso
        fetchCourses(); // Atualiza a tabela de cursos

        // Limpa o formulário
        setCourseName('');
        setShift('');
        setTgCoordinatorReg('');
        setCourseCoordinatorReg('');
        setValidated(false);
        // Reseta os inputs de rádio
        form.reset();

      } catch (error) {
        setErrorMessage(error.message);
        // Mostra o toast de erro
        setShowErrorToast(true);
      }
    };
    // HTML
    return (
      <>
        {/* --- SEÇÃO DO FORMULÁRIO --- */}
        {/* 1. O <div class="container mt-5"> vira <Container> */}
        <Container className="mt-5">
          {/* 2. <div class="row ..."> e <div class="col..."> viram <Row> e <Col> */}
          <Row className="justify-content-center">
            <Col md={6}>
              {/* 3. <div class="card..."> vira <Card> */}
              <Card>
                <Card.Header className="bg-primary text-white">
                  <h3 className="text-center">Cadastro de Curso</h3>
                </Card.Header>
                <Card.Body>
                  {/* 4. <form> vira <Form> com props de validação */}
                  <Form noValidate validated={validated} onSubmit={handleSubmit}>

                    {/* Nome do Curso */}
                    {/* 5. <div class="mb-3"> vira <Form.Group> */}
                    <Form.Group className="mb-3" controlId="formCourseName">
                      <Form.Label>Nome do curso</Form.Label>
                      {/* 6. <input> vira <Form.Control> */}
                      {/* Ele é "controlado" pelo React com 'value' e 'onChange' */}
                      <Form.Control
                        type="text"
                        required
                        value={courseName}
                        onChange={(e) => setCourseName(e.target.value)}
                      />
                      {/* 7. <div class="invalid-feedback"> vira <Form.Control.Feedback> */}
                      <Form.Control.Feedback type="invalid">
                        Nome é um campo obrigatório
                      </Form.Control.Feedback>
                    </Form.Group>

                    {/* Turno (Radios) */}
                    <Form.Group className="mb-3">
                      <Form.Label>Turno: </Form.Label>
                      {/* 8. Radios viram <Form.Check> */}
                      <Form.Check
                        inline
                        type="radio"
                        label="Noite"
                        name="shift-input"
                        id="night"
                        value="NIGHT"
                        onChange={(e) => setShift(e.target.value)}
                        required // Validação de grupo
                      />
                      <Form.Check
                        inline
                        type="radio"
                        label="Manhã"
                        name="shift-input"
                        id="morning"
                        value="MORNING"
                        onChange={(e) => setShift(e.target.value)}
                        required
                      />
                      <Form.Check
                        inline
                        type="radio"
                        label="Tarde"
                        name="shift-input"
                        id="afternoon"
                        value="AFTERNOON"
                        onChange={(e) => setShift(e.target.value)}
                        required
                      />
                    </Form.Group>

                    {/* Professor de TG (Matricula) */}
                    <Form.Group className="mb-3" controlId="formTgCoordinator">
                      <Form.Label>Professor de TG (Matricula)</Form.Label>
                      <Form.Control
                        type="text"
                        required
                        value={tgCoordinatorReg}
                        onChange={(e) => setTgCoordinatorReg(e.target.value)}
                      />
                      <Form.Control.Feedback type="invalid">
                        A matricula do professor de TG é um campo obrigatório
                      </Form.Control.Feedback>
                    </Form.Group>

                    {/* Coordenador do curso (Matricula) */}
                    <Form.Group className="mb-3" controlId="formCourseCoordinator">
                      <Form.Label>Coordenador do curso (Matricula)</Form.Label>
                      <Form.Control
                        type="text"
                        required
                        value={courseCoordinatorReg}
                        onChange={(e) => setCourseCoordinatorReg(e.target.value)}
                      />
                      <Form.Control.Feedback type="invalid">
                        A matricula do coordenador de curso é um campo obrigatório
                      </Form.Control.Feedback>
                    </Form.Group>

                    {/* 9. Botão vira <Button> */}
                    <div className="d-grid">
                      <Button type="submit" variant="primary">Cadastrar</Button>
                    </div>
                  </Form>
                </Card.Body>
              </Card>
            </Col>
          </Row>
        </Container>

        {/* --- SEÇÃO DA TABELA --- */}
        <Container className="mt-5">
          <h2>Cursos Cadastrados</h2>
          {/* 10. <div class="table-responsive"> e <table> vira <Table responsive> */}
          <Table responsive striped bordered hover>
            <thead className="table-dark">
              <tr>
                <th>Nome</th>
                <th>Turno</th>
                <th>Nome Professor TG</th>
                <th>Nome Coordenador</th>
              </tr>
            </thead>
            <tbody>
              {/* 11. O <tbody> é gerado dinamicamente a partir do estado 'courses' */}
              {courses.map((course) => (
                // O 'key' é essencial para o React identificar cada item
                // TODO: Confirme os nomes das propriedades (ex: course.name)
                <tr key={course.id}>
                  <td>{course.name}</td>
                  <td>{course.shift}</td>
                  <td>{course.tgCoordinatorName}</td>
                  <td>{course.courseCoordinatorName}</td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Container>

        {/* --- SEÇÃO DOS TOASTS --- */}
        {/* 12. Os toasts são controlados pelo estado 'show...' */}
        <ToastContainer position="top-end" className="p-3">
          {/* Toast de Sucesso */}
          <Toast
            onClose={() => setShowSuccessToast(false)}
            show={showSuccessToast}
            delay={3000}
            autohide
            bg="success"
          >
            <Toast.Header closeButton={true}>
              <strong className="me-auto">Sucesso!</strong>
            </Toast.Header>
            <Toast.Body className="text-white">Curso cadastrado com sucesso!</Toast.Body>
          </Toast>

          {/* Toast de Erro */}
          <Toast
            onClose={() => setShowErrorToast(false)}
            show={showErrorToast}
            delay={5000}
            autohide
            bg="danger"
          >
            <Toast.Header closeButton={true}>
              <strong className="me-auto">Erro na Requisição</strong>
            </Toast.Header>
            <Toast.Body className="text-white">{errorMessage}</Toast.Body>
          </Toast>
        </ToastContainer>
      </>
    );
  }
export default CreateCourse