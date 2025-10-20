const API_URL = "http://localhost:8080/students/register";

async function registerStudent(formData) {
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formData)
    });
    
    if (response.ok) {
      const data = await response.json();
      showSuccess("Aluno cadastrado com sucesso!");
    } else {
      const error = await response.text();
      showError(error);
    }
  } catch (error) {
    showError("Erro de conexão com o servidor");
  }
}