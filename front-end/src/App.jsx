import './App.css'
import AppNavBar from './components/AppNavBar'
import { Container } from 'react-bootstrap'

function App() {

  return (
    <>
    <Container fluid>
      <div className="row justify-content-center">
        <div class="col-md-8 text-center d-flex-align mt-5">
          <div>
            <h1>Bem-vindo a Plataforma de Gerenciamento de Trabalhos de Graduação </h1>
            <p class="lead">Ajudando Orientadores e Orientandos!</p>
          </div>
          <AppNavBar />
          </div>
        </div>
       </Container>
    </>
  )
}

export default App
