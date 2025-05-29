document.getElementById('formulario-login').addEventListener('submit', function(e) {
    e.preventDefault(); // Evita que el formulario recargue la página
  
    const usuario = document.getElementById('usuario').value;
    const password = document.getElementById('password').value;
  
    // Mostrar los datos (sin validación real)
    document.getElementById('resultado').innerHTML = `
      <h3>Datos ingresados:</h3>
      <p><strong>Usuario:</strong> ${usuario}</p>
      <p><strong>Contraseña:</strong> ${password}</p>
    `;
  });
  