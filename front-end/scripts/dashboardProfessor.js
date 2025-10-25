// Renderizar tabela
function renderStudentsTable(students) {
  const tbody = document.getElementById('students-table-body');
  tbody.innerHTML = students.map(student => `
    <tr>
      <td>${student.name}</td>
      <td>${student.registration}</td>
      <td>${student.email}</td>
      <td>${getStatusBadge(student.status)}</td>
    </tr>
  `).join('');
}

// Badge colorido por status
function getStatusBadge(status) {
  const badges = {
    'PRE_REGISTRATION': 'secondary',
    'PENDING_VERIFICATION': 'warning',
    'EMAIL_CONFIRMED': 'info',
    'ACTIVE': 'success',
    'INACTIVE': 'danger'
  };
  
  const statusLabels = {
    'PRE_REGISTRATION': 'Pré-Cadastro',
    'PENDING_VERIFICATION': 'Verificação Pendente',
    'EMAIL_CONFIRMED': 'Email Confirmado',
    'ACTIVE': 'Ativo',
    'INACTIVE': 'Inativo'
  };
  
  return `<span class="badge bg-${badges[status]}">${statusLabels[status]}</span>`;
}