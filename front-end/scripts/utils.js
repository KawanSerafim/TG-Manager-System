import { courseShiftTranslations } from "../interfaces/coursesShiftTranslations.js";

/**
 * Função responsável por preencher a tabela e exibir a seção de resultados.
 * @param {Response} data - A resposta do backend.
 * @param {HTMLElement} tableBody - O elemento <tbody> da tabela a ser preenchido.
 * @param {HTMLElement} resultsContainer - O contêiner que envolve a tabela.
 */
export function displayResults(data, tableBody, resultsContainer) {
    // Limpa qualquer conteúdo anterior da tabela
    tableBody.innerHTML = '';

    let students = data.students;

    if (students.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="2">Nenhum aluno retornado no processamento, verifique se o arquivo ja foi enviado anteriormente.</td></tr>';
    } else {
        //Verifica se a linha contem o nome e RA de aluno valido
        students = students.filter((student) => student.name != 'RA' && student.registration != 'ALUNO')
        // Cria uma linha (tr) para cada aluno
        students.forEach(student => {
            //Traduz o shift
            const shiftTranslated = courseShiftTranslations[data.shift]
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${student.name}</td>
                <td>${student.registration}</td>
                <td>${data.courseName}</td>
                <td>${data.discipline}</td>
                <td>${shiftTranslated}</td>
                <td>${data.year}</td>
                <td>${data.semester}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    //torna o contêiner de resultados visível!
    resultsContainer.style.display = 'block';
}

 /**
 * Retorna um array com os valores de todos os turnos selecionados.
 * Exemplo: ["NIGHT", "MORNING"]
 */
export function getSelectedShifts() {
  // 1. Seleciona todos os checkboxes com esse nome que estão marcados
  const checkedShifts = document.querySelectorAll('input[name="shift-input[]"]:checked');
  
  // 2. Converte o NodeList (resultado do querySelectorAll) em um array
  const shiftValues = Array.from(checkedShifts);
  
  // 3. Extrai apenas os valores (ex: "NIGHT", "MORNING")
  return shiftValues.map(checkbox => checkbox.value);
}

/**
 * Retorna um array com os valores de todas as disciplinas selecionadas.
 * Exemplo: ["TG1"] ou ["TG1", "TG2"]
 */
export function getSelectedDisciplines() {
  // 1. Seleciona todos os checkboxes com esse nome que estão marcados
  const checkedDisciplines = document.querySelectorAll('input[name="discipline-input[]"]:checked');
  
  // 2. Converte o NodeList em um array
  const disciplineValues = Array.from(checkedDisciplines);
  
  // 3. Extrai apenas os valores
  return disciplineValues.map(checkbox => checkbox.value);
}