import './App.css'
// Componentes
import NavBarApp from './components/NavBarApp';
// Dependencias
import { Routes, Route } from 'react-router-dom';
// Paginas
import Login from './pages/Login';
import SendFileStudentGroup from './pages/SendFileStudentGroup'
import CreateProfessor from './pages/CreateProfessor';
import CreateCourse from './pages/CreateCourse';
import Home from './pages/Home'
import CreateStudent from './pages/CreateStudent';
// Layouts
import HomeLayout from './layouts/HomeLayout'
import DefaultLayout from './layouts/DefaultLayout';

function App() {

  return (
    <div className="App">
      
     
        {/* define onde as páginas serão trocadas */}
        <Routes>
          {/* Todas as rotas "filhas" aqui usarão o HomeLayout */}
          <Route element={<HomeLayout />}> 
            <Route path="/" element={<Home />} />
          </Route> 
          {/* Todas as rotas "filhas" aqui usarão o DefaultLayout */}
          <Route element={<DefaultLayout />}>
            <Route path="/login" element={<Login />} />
            <Route path="/professor/enviar-arquivo" element={<SendFileStudentGroup />} />
            <Route path="/cadastro/professor" element={<CreateProfessor />} />
            <Route path="/cadastro/curso" element={<CreateCourse />} />
            <Route path="/cadastro/aluno" element={<CreateStudent />} />
          </Route>
        </Routes>
    </div>
  )
}

export default App
