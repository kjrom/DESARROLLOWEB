import os
class Config:
    SQLALCHEMY_DATABASE_URI = 'postgresql://tareas_xfvp_user:C5LtHDu4gN71300xmGlOULwNw2vokN7M@dpg-d0e41f8dl3ps73batjq0-a.oregon-postgres.render.com/tareas_xfvp'
    SQLALCHEMY_TRACK_MODIFICATIONS = False

class Config:
    SECRET_KEY = os.environ.get('SECRET_KEY') or 'mikeyultrasecreto'
    SQLALCHEMY_DATABASE_URI = 'postgresql://tareas_xfvp_user:C5LtHDu4gN71300xmGlOULwNw2vokN7M@dpg-d0e41f8dl3ps73batjq0-a.oregon-postgres.render.com/tareas_xfvp'
    SQLALCHEMY_TRACK_MODIFICATIONS = False

