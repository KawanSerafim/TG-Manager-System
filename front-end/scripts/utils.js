export function getSemester(){
  //Retorna em number o mes atual, 0 - Janeiro ... 11- Dezembro
  const month = new Date().getMonth();

  //De janeiro até a julho, primeiro semestre
  if ( month <= 6){
    return '1'
  }
  else{
    //Caso contrario segundo semestre
    return '2'
  }
}

//Função para gerar a senha que o professor vai mandar para os alunos
export function generatedPassword(size = 6, options={}){
  const baseModel = {
    uppers: 'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
    lowers: 'abcdefghijklmnopqrstuvwxyz',
    numbers: '0123456789'
  };

  const settings = {...options}
  let allCharacters = "";

  if (settings.uppers === true) allCharacters += baseModel.uppers
  if (settings.lowers === true) allCharacters += baseModel.lowers
  if (settings.numbers === true) allCharacters += baseModel.numbers

  if (allCharacters.length === 0) {
    throw new Error("Pelo menos um tipo de caractere deve ser selecionado.")
  }

  const randomArray = new Uint32Array(size);
  window.crypto.getRandomValues(randomArray);

  let password = "";
  for (let i = 0; i < size; i++){
    password += allCharacters[randomArray[i] % allCharacters.length];
  }

  return password;


}