from app import create_app, db
from app.models import Libro

app = create_app()

if __name__ == '__main__':
    with app.app_context():
        
        if Libro.query.count() == 0:
            libro1 = Libro(titulo="El Gran Gatsby", autor="F. Scott Fitzgerald", editorial="Charles Scribner's Sons")
            libro2 = Libro(titulo="Cien Años de Soledad", autor="Gabriel García Márquez", editorial="Editorial Sudamericana")
            libro3 = Libro(titulo="Don Quijote de la Mancha", autor="Miguel de Cervantes", editorial="Francisco de Robles")

            db.session.add(libro1)
            db.session.add(libro2)
            db.session.add(libro3)

            db.session.commit()
            print("Libros añadidos exitosamente")
        else:
            print("La base de datos ya tiene libros. No se agregaron duplicados.")

    app.run(debug=True)
