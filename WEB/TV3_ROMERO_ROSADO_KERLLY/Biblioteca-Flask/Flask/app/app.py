from flask import Flask, render_template, redirect, url_for, request, flash, session, jsonify
from werkzeug.security import generate_password_hash, check_password_hash

# Diccionario para guardar usuarios en memoria
usuarios = {}

def create_app():
    app = Flask(__name__)
    app.secret_key = 'my_secret_key'

    @app.route('/', methods=['GET', 'POST'])
    def index():
        if request.method == 'POST':
            if 'login' in request.form:
                username = request.form['username']
                password = request.form['password']
                if username in usuarios and check_password_hash(usuarios[username]['password'], password):
                    session['user'] = username
                    flash("Iniciaste sesión exitosamente", 'success')
                    return redirect(url_for('libros'))
                else:
                    flash("Usuario o contraseña incorrectos", 'danger')

            elif 'register' in request.form:
                username = request.form['username']
                password = request.form['password']
                if username in usuarios:
                    flash("El usuario ya existe", 'danger')
                else:
                    hashed_password = generate_password_hash(password)
                    usuarios[username] = {'password': hashed_password}
                    flash("Te registraste exitosamente. Inicia sesión.", 'success')

        return render_template('index.html')

    @app.route('/libros')
    def libros():
        if 'user' not in session:
            return redirect(url_for('index'))
        return render_template('libros.html', username=session['user'])

    @app.route('/api')
    def api():
        return jsonify({"status": "success", "menssage": "Mi primer proyecto en Flask"})

    return app
