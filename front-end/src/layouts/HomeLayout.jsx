import { Outlet } from 'react-router-dom'; // 1. Importe o Outlet
import AppNavbar from '../components/NavBarApp';

import WelcomeHeader from '../components/WelcomeHeader';

function HomeLayout() {
  return (
    <div>
      {/* 2. Ordem da página Index */}
      <WelcomeHeader />
      <AppNavbar />

      {/* 3. O conteúdo da página Home será injetado aqui */}
      <main className="container mt-4">
        <Outlet />
      </main>
    </div>
  );
}

export default HomeLayout;