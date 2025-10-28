import WelcomeHeader from "../components/WelcomeHeader"
import NavBarApp from "../components/NavBarApp"

function Home() {
  return (
    <>
    {/* O WelcomeHeader e a Navbar já estão no HomeLayout */}
      
    {/* Este é o conteúdo que será injetado no <Outlet /> */}
    <hr />
    <h2>Funcionalidades Principais</h2>
    <p>Cadastros, Dashboards e Desburacritização de Trabalhos de Graduação da FATEC ZL</p>
    </>
  )
}
export default Home