from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from config import Config

db = SQLAlchemy()
migrate = Migrate()

def create_app():
    app = Flask(__name__)

    # Cargar configuración
    app.config.from_object(Config)

    # Inicializar extensiones
    db.init_app(app)
    migrate.init_app(app, db)

    # Importar modelos para que Flask-Migrate los detecte
    from app import models

    # Importar y registrar Blueprints
    from app.routes.libros import libros_bp
    from app.routes.home import home_bp

    app.register_blueprint(libros_bp)
    app.register_blueprint(home_bp)

    return app
