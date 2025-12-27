package com.mycompany.pi.controller;

import com.mycompany.pi.UsuarioDAO;
import com.mycompany.pi.Usuarios;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("login"); 
        String senha = request.getParameter("senha");
        
        Usuarios userTentativa = new Usuarios();
        userTentativa.setLogin(email);
        userTentativa.setSenha(senha);
        
        Usuarios logado = UsuarioDAO.validarUsuario(userTentativa);
        
        if (logado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", logado);
            
            if (logado.getTipo() != null && logado.getTipo().equalsIgnoreCase("profissional")) {
                response.sendRedirect("dashboard_profissional.html");
            } else {
                response.sendRedirect("dashboard_cliente.html");
            }
        } else {
            response.sendRedirect("login.html?erro=1");
        }
    }
}