import { BrowserRouter, Routes, Route } from 'react-router-dom'
import AuthProvider from './contexts/AuthContext'
import './App.css'
import Header from './components/Header'
import Footer from './components/Footer'
import Home from './pages/Home'
import Signup from './pages/Signup'
import SignupResult from './pages/SignupResult'
import Login from './pages/Login'
import MyInfo from './pages/MyInfo'
import Modify from './pages/Modify'
import Product from './pages/Product'

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <div className='App'>
          <Header/>
          <nav id="nav_wrap">
            <h2>EXAMPLE</h2>
          </nav>
          <main>
            <Routes>
              <Route path='/' element={<Home/>}/>
              <Route path='/member/signup' element={<Signup/>}/>
              <Route path='/member/signup_result' element={<SignupResult/>}/>
              <Route path='/member/login' element={<Login/>}/>
              <Route path='/member/myinfo' element={<MyInfo/>}/>
              <Route path='/member/modify' element={<Modify/>}/>
              <Route path='/cars/insert' element={<Product/>}/>
            </Routes>
          </main>
          <Footer/>
        </div>
      </BrowserRouter>
    </AuthProvider>
  )
}

export default App
