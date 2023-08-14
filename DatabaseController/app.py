from flask import Flask
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
    query = "SELECT * FROM usert;"
    cursor.execute(query)
    table = cursor.fetchall()
    return str(table)


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
