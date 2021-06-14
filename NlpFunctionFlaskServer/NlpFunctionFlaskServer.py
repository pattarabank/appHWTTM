from pythainlp.tokenize import word_tokenize
from flask import Flask
from flask import request

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'HelloWorld'

# test get

@app.route('/one')
def hello_one():
    return 'Hello 1'



@app.route('/post', methods=["POST"])
def hello_post():
    value = request.form['value']
    data = word_tokenize(value, engine="newmm")
    return str(data)[1:-1]


if __name__ == "__main__":
    app.run(host='0.0.0.0')
