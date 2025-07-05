
# 🚀 Usage

To run the chatbot you have to run the web server and the agent

## ▶️ Run the web server

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

To install all necessary packages, run the following command:

```bash
npm install
```

This should generate `node_modules` folder.

To run the app in the development mode, use the following command:

```bash
npm start
```

Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.

## 🧠 Run the agent

The agent runs a Flask server that loads the trained intent classification model and handles user requests.

To start the backend:
```bash
python music_chatbot.py
```

Make sure you have installed all Python dependencies using:

```bash
pip install -r requirements.txt
```

The agent will listen by default on http://localhost:5000 and communicate with the frontend via Socket.IO.



