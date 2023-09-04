from flask import Flask, request
import psycopg2

app = Flask(__name__)

db_params = {
    "host": "postgres",
    "port": 5432,
    "database": "dbname",
    "user": "amigoscode",
    "password": "password"
}


@app.route('/')
def hello_world():
    connection = psycopg2.connect(**db_params)
    cursor = connection.cursor()
    query = "SELECT * FROM frauds;"
    cursor.execute(query)
    table = cursor.fetchall()
    return str(table)


@app.route('/reg')
def reg():
    connection = psycopg2.connect(**db_params)
    cursor = connection.cursor()
    query = "SELECT * FROM registered;"
    cursor.execute(query)
    table = cursor.fetchall()
    return str(table)


@app.route('/validate', methods=['POST'])
def check_user():
    request_data = request.get_json()

    first_name = request_data.get('FirstName')
    last_name = request_data.get('LastName')
    password = request_data.get('Pass')
    connection = psycopg2.connect(**db_params)
    cursor = connection.cursor()
    query = "SELECT * FROM frauds WHERE firstname = %s AND lastname = %s;"
    cursor.execute(query, (first_name, last_name))
    user = cursor.fetchone()
    if not user:
        # If the user does not exist, insert them into the database
        insert_query = "INSERT INTO registered (firstname, lastname, password) VALUES (%s, %s, %s);"
        cursor.execute(insert_query, (first_name, last_name, password))
        connection.commit()
        cursor.close()
        connection.close()
        return "True"
    else:
        cursor.close()
        connection.close()
        return "False"





"""import psycopg2
from flask import Flask

db_params = {
    "host": "postgres",
    "port": 5432,
    "database": "dbname",
    "user": "amigoscode",
    "password": "password"
}

app = Flask(__name__)


@app.route('/test', methods=['GET'])
def test():
    connection = psycopg2.connect(**db_params)
    cursor = connection.cursor()
    query = "SELECT * FROM usert;"
    cursor.execute(query)
    table = cursor.fetchall()
    if connection:
        cursor.close()
        connection.close()
    return str(table)


@app.route('/ver', methods=['GET'])
def mainsite():
    return "main"


app.run(port=3000)


"""
"""
try:
    connection = psycopg2.connect(**db_params)
    cursor = connection.cursor()
    query = "SELECT * FROM usert;"
    cursor.execute(query)
    table = cursor.fetchall()
    print("Tables: ", table)

except (Exception, psycopg2.Error) as error:
    print("Error while connecting to PostgreSQL:", error)

finally:
    if connection:
        cursor.close()
        connection.close()
        print("PostgreSQL connection closed.")

"""
