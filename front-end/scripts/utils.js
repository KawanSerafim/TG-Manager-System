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