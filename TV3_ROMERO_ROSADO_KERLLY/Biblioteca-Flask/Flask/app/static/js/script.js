document.addEventListener('DOMContentLoaded', function() {
    console.log("Página cargada correctamente");

    // Animación al enviar el formulario
    const form = document.querySelector('form');
    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();  // Evita el envío real del formulario

            alert('¡Formulario Enviado Exitosamente!');
            form.reset(); // Resetea los campos después del envío
        });
    }

    // Hover effecto para los botones de los libros
    const buttons = document.querySelectorAll('.book-item button');
    buttons.forEach(button => {
        button.addEventListener('mouseenter', function() {
            button.style.backgroundColor = '#45a049';
        });
        button.addEventListener('mouseleave', function() {
            button.style.backgroundColor = '#4CAF50';
        });
    });
});
