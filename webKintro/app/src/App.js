import { createContext, useContext, useEffect, useState } from 'react';
import Shared from 'shared';
import './App.css';

const SharedContext = createContext();
const useSharedContext = () => useContext(SharedContext);

function SharedProvider({ children }) {
  const shared = Shared.dev.ybonnetain.kintro.webkintro.Shared;

  return (
    <SharedContext.Provider value={{ shared }}>
      {children}
    </SharedContext.Provider>
  )
}

function App() {
  return (
    <SharedProvider>
      <div className="App">
        <Counter />
      </div>
    </SharedProvider>
  );
}

function Counter() {
  const { shared } = useSharedContext();
  const [count, setCount] = useState(null);

  const increment = () => shared.incrementCounter();
  const decrement = () => shared.decrementCounter();
  const reset = () => shared.resetCounter();

  useEffect(() => {
    shared.observeCounter(setCount)
  });

  return (
    <div className="Counter-container">
      <span>{count}</span>
      <div className="Counter-buttons-container">
        <button onClick={increment}>increment</button>
        <button onClick={decrement}>decrement</button>
        <button onClick={reset}>reset</button>
      </div>
    </div>
  );
}

export default App;
