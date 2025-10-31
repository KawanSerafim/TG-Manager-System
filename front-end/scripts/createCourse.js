import { courseShiftTranslations } from "../interfaces/coursesShiftTranslations.js";
import { getProfessorsCourse, getProfessorsTG } from "./serviceProfessor.js";

const API_URL = "http://localhost:8080/courses/api";


async function putProfessoresTG(){
   const professorsTG = await getProfessorsTG();

    // pROFESSORES DE TG
   const selectTG = document.getElementById('tgCoordinatorRegistration');
   const optionsHTML = professorsTG.map(professor => {
    return `
         <option class="option" value= ${professor.registration}>
            ${professor.name}
        </option>
    `;
   }).join('')
   selectTG.innerHTML += optionsHTML;
}

async function putProfessoresCourse(){
   const professorsCourse = await getProfessorsCourse();

    // pROFESSORES DE TG
   const select = document.getElementById('courseCoordinatorRegistration');
   const optionsHTML = professorsCourse.map(professor => {
    return `
         <option class="option" value= ${professor.registration}>
            ${professor.name}
        </option>
    `;
   }).join('')
   select.innerHTML += optionsHTML;
}

// Garante que todo o código que manipula o DOM só rode depois que a página estiver pronta
document.addEventListener('DOMContentLoaded', () => {
    
    putProfessoresTG()
    putProfessoresCourse()
    // --- INICIALIZAÇÃO E SELETORES ---
    const form = document.getElementById('form');
    const toastSuccessEl = document.getElementById('toast-sucesso');
    const toastErrorEl = document.getElementById('toast-erro');
    
    // Verificação de segurança: veja se os elementos realmente existem
    if (!form || !toastSuccessEl || !toastErrorEl) {
        console.error("Erro crítico: Formulário ou elementos de toast não encontrados no HTML. Verifique os IDs.");
        return; // Para a execução se algo estiver faltando
    }

    // Cria as instâncias do Toast que serão usadas depois
    const toastSuccess = new bootstrap.Toast(toastSuccessEl);
    const toastError = new bootstrap.Toast(toastErrorEl);

    // --- CONFIGURAÇÃO DO EVENTO DE SUBMIT ---
    // Este bloco foi movido para DENTRO do DOMContentLoaded
    form.addEventListener('submit', (event) => {
        event.preventDefault();
        
        //Ativa validações do forms
        if (!form.checkValidity()) {
            event.stopPropagation();
        } else{
          //Se ta tudo certo
          // Captura dos dados do formulário
          const courseData = {
            name: document.getElementById('name').value,
            shift: document.querySelector('input[name="shift-input"]:checked').value,
            tgCoordinatorRegistration: document.getElementById('tgCoordinatorRegistration').value,
            courseCoordinatorRegistration: document.getElementById('courseCoordinatorRegistration').value
          }; 
          // Agora, ao chamar createProfessor, as variáveis toastSuccess e toastError GARANTIDAMENTE existem
          createCourse(courseData, toastSuccess, toastError);
        }
        // 5. Adiciona a classe para exibir os estilos de feedback (vermelho/verde)
        form.classList.add('was-validated');
    });
});


// --- FUNÇÕES AUXILIARES ---

// A função de fetch agora recebe as instâncias do toast como parâmetros para ficar mais limpa
async function createCourse(courseData, toastSuccess, toastError) {
    try {
        let createURL = API_URL + "/create";

        const response = await fetch(createURL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(courseData)
        });
        
        if (response.ok) {
            const newCourse = await response.json(); 
            toastSuccess.show();
            addCourseInTable(newCourse);
            //Apaga os campos
            document.getElementById('form').reset();
            //Retira validação até o usuario enviar novamente
            form.classList.remove('was-validated');
        } else {
            const error = await response.text();
            document.querySelector('#toast-erro .toast-body').textContent = error;
            toastError.show();
        }
    } catch (error) {
        document.querySelector('#toast-erro .toast-body').textContent = "Erro de conexão com o servidor.";
        toastError.show();
    }
}

function addCourseInTable(course) {
    // 1. Encontra o corpo da tabela pelo ID
    const tablebody = document.getElementById('tabela-professores-corpo');

    // 2. Insere uma nova linha (tr) no final do corpo da tabela
    const newLine = tablebody.insertRow();

    // 3. Insere as células (td) na nova linha
    const celName = newLine.insertCell();
    const celShift = newLine.insertCell();
    const celProfTGName = newLine.insertCell();
    const celAdvisorName = newLine.insertCell();

    
    // 4. Preenche o conteúdo de cada célula com os dados do professor
    celName.textContent = course.name;
    //Traduz o turno
    let courseShiftTranslated = courseShiftTranslations[course.shift];
    celShift.textContent = courseShiftTranslated;
    celProfTGName.textContent = course.tgCoordinator.name;
    celAdvisorName.textContent = course.courseCoordinator.name;

}
