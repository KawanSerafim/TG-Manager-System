//Inicia a bilbioteca de icones
lucide.createIcons()
//Função para preencher o combo box de cursos, chamada ao carregar a página
document.addEventListener('DOMContentLoaded', () => {

  courses = [ 'AMS - Análise e Desenvolvimento de Sistemas', 
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

let select = document.querySelector('.select'),
selectedValue = document.getElementById('selected-value'),
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
})



//Capturar o xls do form
//botão de envio
const btn = document.getElementById("btn-upload");
//div de mensagem
const statusMensagem = document.getElementById('status-mensagem');

//listener acionado ao clicar no botão de envio
btn.addEventListener('click', async () => {
  //pega o csv enviado
  const file = document.getElementById("btn-file").files[0];
  const select = document.getElementById('selected-value').textContent;
  //TODO: Pegar o input selecionado

  // verificação caso o input de file não tiver sido enviado nada
  if (!file) {
    statusMensagem.textContent = "Por favor selecione um arquivo XLS/XLSX"
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
  const year = new Date().getFullYear();
  formData.append('year', year)
  //TODO: função para gerar semestre ou fazer o usuario selecionar no html
  formData.append('semester', '3')

  // Limpa a mensagem de status anterior
  statusMensagem.textContent = 'Enviando...';
  statusMensagem.style.color = 'black';

  //Fazer requisição para o backend
  //enviar para o backend o csv
  const API_URL = "http://localhost:8080/api/students/upload-csv"
  try{
    const response = await fetch(API_URL, {
      method: "POST",
      body: formData,
      //Content-type é definido pelo navegador ao usar FormData
    })
    //informar no front que deu certo
    if(response.ok){
      console.log(response.status)
      if (response.status === 204){
        statusMensagem.style.color = 'green';
        statusMensagem.textContent = 'Alunos pré-cadastrados com sucesso!'
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
