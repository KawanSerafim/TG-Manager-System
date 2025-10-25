import { professorsRoleTranslation } from "../interfaces/professorsRoleTranslation.js";

const API_URL = "http://localhost:8080/professors/api/create";

// Garante que todo o código que manipula o DOM só rode depois que a página estiver pronta
document.addEventListener('DOMContentLoaded', () => {

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
          const professorData = {
            name: document.getElementById('name').value,
            registration: document.getElementById('registration').value,
            email: document.getElementById('email').value,
            password: document.getElementById('password').value,
            role: document.getElementById('role').value
          }; 
          // Agora, ao chamar createProfessor, as variáveis toastSuccess e toastError GARANTIDAMENTE existem
          createProfessor(professorData, toastSuccess, toastError);
        }
        // 5. Adiciona a classe para exibir os estilos de feedback (vermelho/verde)
        form.classList.add('was-validated');
    });
    // Encontra o input de confirmação de senha
    const confirmPasswordInput = document.getElementById('confirmation-password');

    // Adiciona o "escutador" para o evento 'blur'
    confirmPasswordInput.addEventListener('blur', validatePasswords);

    // Opcional, mas recomendado: validar também quando o usuário digita na primeira senha
    const passwordInput = document.getElementById('password');
    passwordInput.addEventListener('keyup', validatePasswords);
});


// --- FUNÇÕES AUXILIARES ---

// A função de fetch agora recebe as instâncias do toast como parâmetros para ficar mais limpa
async function createProfessor(professorData, toastSuccess, toastError) {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(professorData)
        });
        
        if (response.ok) {
            const novoProfessor = await response.json(); 
            toastSuccess.show();
            addProfessorInTable(novoProfessor);
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

function addProfessorInTable(professor) {
    // 1. Encontra o corpo da tabela pelo ID
    const tableBody = document.getElementById('tabela-professores-corpo');

    // 2. Insere uma nova linha (tr) no final do corpo da tabela
    const newLine = tableBody.insertRow();

    // 3. Insere as células (td) na nova linha
    const celName = newLine.insertCell();
    const celRegistration = newLine.insertCell();
    const celEmail = newLine.insertCell();
    const celRole = newLine.insertCell();

    // 4. Preenche o conteúdo de cada célula com os dados do professor
    celName.textContent = professor.name;
    celRegistration.textContent = professor.registration;
    celEmail.textContent = professor.email;
    //Traduz o Role/Cargo
    let roleTranslated = professorsRoleTranslation[professor.role];
    celRole.textContent = roleTranslated;

}


function validatePasswords(){
  let passwordInput = document.getElementById('password'),
  confirmPasswordInput = document.getElementById('confirmation-password');
  // valores digitados nos inputs
  const passwordValue = passwordInput.value;
  const confirmPasswordValue = confirmPasswordInput.value;

  // 3. Compara os valores
  // Só valida se o campo de confirmação tiver algo digitado
  if (confirmPasswordValue !== "" && passwordValue !== confirmPasswordValue) {
      // Se as senhas forem diferentes, adiciona a classe de erro do Bootstrap
      confirmPasswordInput.classList.add('is-invalid');
      confirmPasswordInput.classList.remove('is-valid');
  } else {
      // Se as senhas forem iguais (ou o campo estiver vazio), remove a classe de erro
      confirmPasswordInput.classList.remove('is-invalid');
      // Opcional: Adiciona uma classe de sucesso se o campo não estiver vazio
      if (confirmPasswordValue !== "") {
          confirmPasswordInput.classList.add('is-valid');
      }
  }
}