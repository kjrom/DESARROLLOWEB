from app import db

class Usuario(db.Model):
    __tablename__ = "Usuarios"
    
    id = db.Column(db.Integer, primary_key=True)
    nombre = db.Column(db.String(100), nullable=False)
    correo = db.Column(db.String(100), nullable=False, unique=True)

class Libro(db.Model):
    __tablename__ = "Libros"
    
    id = db.Column(db.Integer, primary_key=True)
    titulo = db.Column(db.String(200), nullable=False)
    autor = db.Column(db.String(200), nullable=True)
    editorial = db.Column(db.String(200), nullable=True)
