from flask import Blueprint, request, render_template, redirect, url_for, flash
from app.models import Libro
from app import db

libros_bp = Blueprint("libros", __name__)

# Ruta principal para mostrar los libros y agregar uno nuevo
@libros_bp.route("/libros", methods=["GET", "POST"])
def libros():
    if request.method == "POST":
        # Obtener datos del formulario
        titulo = request.form.get('titulo')
        autor = request.form.get('autor')
        editorial = request.form.get('editorial')

        # Crear un nuevo libro
        nuevo_libro = Libro(titulo=titulo, autor=autor, editorial=editorial)

        # Agregar el libro a la base de datos
        db.session.add(nuevo_libro)
        db.session.commit()

        flash("¡Libro agregado con éxito!", "success")
        return redirect(url_for('libros.libros'))  # Redirige a la misma página para ver los libros

    # Obtener todos los libros de la base de datos
    libros = Libro.query.all()

    # Renderizar la página y pasarle los libros
    return render_template('libros.html', libros=libros)

# Ruta para eliminar un libro
@libros_bp.route("/libros/eliminar/<int:id>", methods=["GET"])
def eliminar_libro(id):
    libro = Libro.query.get(id)
    if libro:
        db.session.delete(libro)
        db.session.commit()
        flash("¡Libro eliminado con éxito!", "danger")
    return redirect(url_for('libros.libros'))

# Ruta para editar un libro
@libros_bp.route("/libros/editar/<int:id>", methods=["GET", "POST"])
def editar_libro(id):
    libro = Libro.query.get(id)
    if not libro:
        flash("Libro no encontrado", "warning")
        return redirect(url_for('libros.libros'))

    if request.method == "POST":
        # Obtener los nuevos valores del formulario
        libro.titulo = request.form.get('titulo')
        libro.autor = request.form.get('autor')
        libro.editorial = request.form.get('editorial')

        # Guardar los cambios
        db.session.commit()
        flash("¡Libro editado con éxito!", "success")
        return redirect(url_for('libros.libros'))

    return render_template('editar_libro.html', libro=libro)
