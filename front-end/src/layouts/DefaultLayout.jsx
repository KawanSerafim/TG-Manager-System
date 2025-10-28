// src/layouts/DefaultLayout.jsx
import { Outlet } from 'react-router-dom'; // 1. Importe o Outlet
import AppNavbar from '../components/NavBarApp';

function DefaultLayout() {
  return (
    <div>
      {/* 2. Ordem padrão: Navbar no topo */}
      <AppNavbar />
      
      {/* 3. O conteúdo (Login, Cadastro, etc.) será injetado aqui */}
      <main className="container mt-4">
        <Outlet />
      </main>
    </div>
  );
}

export default DefaultLayout;