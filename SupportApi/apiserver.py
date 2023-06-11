from flask import Flask, request, jsonify
from bardapi import Bard
from underthesea import sentiment

app = Flask(__name__)
@app.route('/askbard', methods=['POST'])
def example():
    if request.is_json:
        data = request.get_json()  # Get the JSON data from the request

        question = data.get('question')  # Access the 'message' key in the JSON data
        context = data.get('context')  # Access the 'message' key in the JSON data
        content = question + " " + context

        token = 'my-token'
        bard = Bard(token=token)
        answer = bard.get_answer(content)['content']

        response = {'status': 'success', 'message': answer}
        return jsonify(response)
    else:
        response = {'status': 'error', 'message': 'Invalid JSON'}
        return jsonify(response), 400

@app.route('/sentiment', methods=['POST'])
def analyze_sentiment():
    data = request.json
    text = data['text']
    result = sentiment(text)
    return jsonify({'sentiment': result})

if __name__ == '__main__':
    app.run()
