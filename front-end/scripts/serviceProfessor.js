const API_URL = "http://localhost:8080";

export async function getProfessorsTG(){
    let data;
    try {
        let ProfessorURL = API_URL + "/professors/api/list/tg-coordinators";
        const response = await fetch(ProfessorURL, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });
        
        if (response.ok) {
            data = await response.json();
        } else {
            const error = await response.text();
            document.querySelector('#toast-erro .toast-body').textContent = error;
            toastError.show();
        }
    } catch (error) {
        document.querySelector('#toast-erro .toast-body').textContent = "Erro de conexão com o servidor, não foi possivel buscar os professores de TG.";
        toastError.show();
    }
    finally{
        console.info(data) 
        return data;
    }
}

export async function getProfessorsCourse(){
    let data;
    try {
        let getProfessorTGURL = API_URL + "/professors/api/list/course-coordinators";
        const response = await fetch(getProfessorTGURL, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });
        
        if (response.ok) {
            data = await response.json();
        } else {
            const error = await response.text();
            document.querySelector('#toast-erro .toast-body').textContent = error;
            toastError.show();
        }
    } catch (error) {
        document.querySelector('#toast-erro .toast-body').textContent = "Erro de conexão com o servidor, não foi possivel buscar os Coordenadores de curso.";
        toastError.show();
    }
    finally{
        console.info(data) 
        return data;
    }
}