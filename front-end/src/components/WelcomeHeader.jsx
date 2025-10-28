import { Container, Row, Col } from "react-bootstrap"

function WelcomeHeader() {
  return(
    <Container>
      <Row className="justify-content-center">
      <Col md={8} className="text-center d-flex-align mt-5">
        <div>
          <h1>Bem-vindo a Plataforma de Gerenciamento de Trabalhos de Graduação </h1>
          <p className="lead">Ajudando Orientadores e Orientandos!</p>
        </div>
      </Col>
      </Row>
    </Container>
  )
}

export default WelcomeHeader