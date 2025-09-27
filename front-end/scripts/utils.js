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