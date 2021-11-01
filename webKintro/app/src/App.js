import logo from './logo.svg';
import './App.css';
import Shared from 'shared/kintro-webKintro'

function App() {
  console.log(Shared.dev.ybonnetain.kintro.webkintro.coucou())
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
