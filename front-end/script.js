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
  const API_URL = "http://localhost:8080/api/students"
  try{
    const response = await fetch(API_URL, {
      method: "POST",
      body: formData,
      //Content-type é definido pelo navegador ao usar FormData
    })
    //informar no front que deu certo
    if(response.ok){
      const result = await response.text();
      statusMensagem.textContent = result;
      statusMensagem.style.color = 'green';
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
