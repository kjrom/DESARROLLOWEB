from flask import Blueprint, jsonify, render_template

home_bp = Blueprint('home', __name__, template_folder='templates')

@home_bp.route('/')
def index():
    return render_template('index.html')
