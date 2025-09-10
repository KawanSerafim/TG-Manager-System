
//Função para preencher o combo box de cursos
function handleCourses(){
  courses = [ 'ADS', 'DSM', 'LOG', 'CME']
  const select = document.getElementById('coursesSelect');
  for (const course of courses) {
    select.innerHTML += `<option value='${course}'>${course}</option>`

  }
}
addEventListener("DOMContentLoaded", handleCourses)


//Capturar o csv do form
//botão de envio
const btn = document.getElementById("btn-upload");
//div de mensagem
const statusMensagem = document.getElementById('status-mensagem');

//listener acionado ao clicar no botão de envio
btn.addEventListener('click', async () => {
  //pega o csv enviado
  const file = document.getElementById("btn-file").files[0];

  // verificação caso o input de file não tiver sido enviado nada
  if (!file) {
    statusMensagem.textContent = "Por favor selecione um arquivo CSV"
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
