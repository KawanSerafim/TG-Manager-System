const API_URL = "http://localhost:8080/auth/login";

document.getElementById('login-form').addEventListener('submit', async (e) => {
  e.preventDefault();
  
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const errorDiv = document.getElementById('error-message');
  
  try {
    const response = await fetch(API_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email, password })
    });
    
    if (response.ok) {
      const data = await response.json();
      
      // Salvar dados no localStorage
      localStorage.setItem('authToken', data.token);
      localStorage.setItem('userData', JSON.stringify({
        name: data.name,
        email: data.email,
        userType: data.userType
      }));
      
      // Redirecionar baseado no tipo de usuário
      redirectByUserType(data.userType);
    } else {
      const error = await response.text();
      showError(error);
    }
  } catch (error) {
    showError('Erro de conexão com o servidor');
  }
});

function redirectByUserType(userType) {
  const redirects = {
    'ADMIN': '/pages/admin/create_professor.html',
    'PROFESSOR': '/index.html',
    'STUDENT': '/pages/student/home.html' // Futuro
  };
  
  window.location.href = redirects[userType] || '/';
}

function showError(message) {
  const errorDiv = document.getElementById('error-message');
  errorDiv.textContent = message;
  errorDiv.classList.remove('d-none');
}

// Inicializar ícones Lucide
lucide.createIcons();