//Inicia a bilbioteca de icones
lucide.createIcons()
//Função para preencher o combo box de cursos, chamada ao carregar a página
document.addEventListener('DOMContentLoaded', () => {

  courses = [ 'ADS', 'DSM', 'LOG', 'CME']
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




//Capturar o csv do form
//botão de envio
const btn = document.getElementById("btn-upload");
//div de mensagem
const statusMensagem = document.getElementById('status-mensagem');

//listener acionado ao clicar no botão de envio
btn.addEventListener('click', async () => {
  //pega o csv enviado
  const file = document.getElementById("btn-file").files[0];
  const select = document.getElementById('coursesSelect').value;

  // verificação caso o input de file não tiver sido enviado nada
  if (!file) {
    statusMensagem.textContent = "Por favor selecione um arquivo CSV"
    statusMensagem.style.color = "red";
    return;
  }

  // verificação caso o input de curso não tiver sido selecionado
  if (!select) {
    statusMensagem.textContent = "Por favor selecione um curso"
    statusMensagem.style.color = "red";
    return;
  }

  //Cria o objeto para ser enviado na requisição
  const formData = new FormData();
  formData.append('file', file);

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
      const result = await response.json();
      statusMensagem.style.color = 'green';
      //name, registration
      statusMensagem.innerHTML = 'Alunos cadastrados: <br>'
      for(let i = 0; i < result.length; i++) {
        statusMensagem.innerHTML += `Nome: ${result[i].name} | RA: ${result[i].registration}`;
        statusMensagem.innerHTML += `<br>`;
      }
    } else {
      const erro = response.text();
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
