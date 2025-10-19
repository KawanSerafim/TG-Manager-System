import { getSemester, displayResults } from "./utils.js";

//Inicia a bilbioteca de icones
lucide.createIcons()
//Função para preencher o combo box de cursos, chamada ao carregar a página
document.addEventListener('DOMContentLoaded', () => {

let courses = [ 'AMS - Análise e Desenvolvimento de Sistemas', 
              'ADS - Análise e Desenvolvimento de Sistemas', 
              'Comércio Exterior', 
              'Desenvolvimento de Produtos Plásticos', 
              'Desenvolvimento de Software Multiplataforma', 
              'Gestão de Recursos Humanos', 
              'Gestão Empresarial', 
              'Logística', 
              'Polímeros'
            ]

  const options = document.getElementById('options');
  const viewButton = document.getElementById('options-view-button');
  let htmlContent = ''
  for (const course of courses) {
    htmlContent += `
    <li class="option">
      <input
            type="radio"
            name="course"
            value="${course}"
            data-label="${course}"
      >

            <i data-lucide="graduation-cap"></i>
            <span class="label">${course}</span>
            <i data-lucide="check"></i>
    </li>
    `;
  }
   //Adiciona os cursos ao html
  options.innerHTML = htmlContent;
  //Chama a biblioteca de icons novamente para garantir a renderização com innerhmtl
  lucide.createIcons();
  
  //toggle do select de cursos
  viewButton.addEventListener('click', () =>{
    options.classList.toggle("open")
  })

let selectedValue = document.getElementById('selected-value'),
optionsViewButton = document.getElementById('options-view-button'),
inputsOptions = document.querySelectorAll('.option input')

inputsOptions.forEach(input => {
  input.addEventListener('click', event => {
    selectedValue.textContent = input.dataset.label

    const isMouseOrTouch =
    event.pointerType == 'mouse' ||
    event.pointerType == 'touch'

    isMouseOrTouch && optionsViewButton.click()
  })
})
//Setar ano e semestre atual
const yearSemesterDiv = document.querySelector('.year-semester');
const currentYear = new Date().getFullYear();
const currentSemester = getSemester();
yearSemesterDiv.innerHTML += `<span>Ano: ${currentYear} - Semestre: ${currentSemester}</span>`;
})

//exibir nome do arquivo ao selecionar um:
const file = document.getElementById("btn-file");
file.addEventListener('change', (event) => {
  if (file.files[0]){
    const label = document.querySelector('.lb-file');
    label.textContent = file.files[0].name;
  }
})

//Capturar o xls do form
//botão de envio
const btn = document.getElementById("btn-upload");
//div de mensagem
const statusMensagem = document.getElementById('status-mensagem');
// --- SELEÇÃO DOS ELEMENTOS DE RESULTADO ---
const resultsContainer = document.getElementById('results-container');
const tableBody = document.getElementById('student-table-body');


//listener acionado ao clicar no botão de envio
btn.addEventListener('click', async () => {
  //pega o csv enviado
  const file = document.getElementById("btn-file").files[0];
  //Pega o nome do curso do input selecionado
  const select = document.getElementById('selected-value').textContent;
  //Pega o input de disciplina selecionado
  const discipline1 = document.getElementById('discipline1')
  const discipline2 = document.getElementById('discipline2')
  let selectedDiscipline = ""
  if (discipline1.checked == true){
    selectedDiscipline = discipline1.value
  } else if(discipline2.checked == true) {
    selectedDiscipline = discipline2.value
  }
  
  
  //Verifica se a disciplina foi selecionada
  if (selectedDiscipline === ""){
      statusMensagem.textContent = "Por favor selecione a qual discipline, TG1 ou TG2, esse arquivo pertence";
      statusMensagem.style.color = "red"
      return;
  }

  // verificação caso o input de file não tiver sido enviado nada ou arquvo não for xlsx
  if (!file || !file.name.endsWith('xlsx') ) {
    statusMensagem.textContent = "Por favor selecione um arquivo .XLSX"
    statusMensagem.style.color = "red";
    return;
  }

  // verificação caso o input de curso não tiver sido selecionado
  if (!select) {
    statusMensagem.textContent = "Por favor selecione um curso"
    statusMensagem.style.color = "red";
    return;
  }
  console.log(select)

  //Cria o objeto para ser enviado na requisição
  const formData = new FormData();
  //append.('nome do parametro', parametro)
  formData.append('file', file);
  formData.append('courseName', select)
  formData.append('discipline', selectedDiscipline)
  

  // Limpa a mensagem de status anterior
  statusMensagem.textContent = 'Enviando...';
  statusMensagem.style.color = 'black';

  //Fazer requisição para o backend
  //enviar para o backend o csv
  const API_URL = "http://localhost:8080/student-group/api"
  try{
    const response = await fetch(API_URL, {
      method: "POST",
      body: formData,
      //Content-type é definido pelo navegador ao usar FormData
    })
    //informar no front que deu certo
    if(response.ok){
      console.log(response.status)
      if (response.status === 201){
        const data = await response.json();
        statusMensagem.textContent = 'Planilha processada com sucesso!';
        statusMensagem.style.color = 'green';
        console.info(data.students)
        // Chama a função para popular e exibir a tabela com os dados recebidos
        displayResults(data.students, tableBody, resultsContainer);
    }         
    } else {
      const erro = await response.text();
      //Informar no front se deu errado
      statusMensagem.textContent = `Erro ${erro}`;
      statusMensagem.style.color = 'red';
    }
    //Capturar erros de rede
  } catch(error){
    console.error('Erro de rede ao tentar enviar arquivo', error);
    statusMensagem.textContent = 'Erro de conexão. Não foi possível enviar o arquivo.';
    statusMensagem.style.color = 'red';
  }
});
