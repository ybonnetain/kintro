import { createContext, useContext, useEffect, useState } from 'react'
import Shared from 'kintro-shared'
import './App.css'

const SharedContext = createContext()
const useSharedContext = () => useContext(SharedContext)

function SharedProvider({ children }) {
  const shared = Shared.dev.ybonnetain.kintro.Main

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
        <Todos />
      </div>
    </SharedProvider>
  )
}

function Counter() {
  const { shared } = useSharedContext()
  const [count, setCount] = useState(null)

  const increment = () => shared.incrementCounter()
  const decrement = () => shared.decrementCounter()
  const reset = () => shared.resetCounter()

  useEffect(() => {
    shared.observeCounter(setCount)
    return () => shared.cancel()
  }, [shared]);

  return (
    <>
      <h2 className="container-title">Counter</h2>
      <div className="container">
        <span>{count}</span>
        <div className="Counter-buttons-container">
          <button onClick={increment}>increment</button>
          <button onClick={decrement}>decrement</button>
          <button onClick={reset}>reset</button>
        </div>
      </div>
    </>
  );
}

function Todos() {
  const { shared } = useSharedContext()
  const [loading, setLoading] = useState(false)
  const [todos, setTodos] = useState([])

  useEffect(() => {
    shared.loadTodos()
    shared.observeStore(onStateChange)
    return () => shared.cancel()
  }, [shared]);

  const onStateChange = (state) => {
    setLoading(state.loading)
    setTodos(state.todos)
  }

  return (
    <>
      <h2 className="container-title">Todos</h2>
      <div className="container">
        {loading && <p>loading</p>}
        <ul className="buttons-container">
          {todos.map(t => <li key={t.id}>{t.title}</li>)}
        </ul>
      </div>
    </>
  )

}

export default App
