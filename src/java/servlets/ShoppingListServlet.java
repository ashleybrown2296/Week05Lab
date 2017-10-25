/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 679918
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String username = (String) session.getAttribute("username");
        if (action != null) {
            if (action.equals("logout")) {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        } else if (action == null) {
            if (username != null) {
                ArrayList items = (ArrayList<String>) session.getAttribute("items");
                if (items == null) {
                    items = new ArrayList<String>();
                }
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
                return;
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("name");
        ArrayList<String> items = (ArrayList<String>) session.getAttribute("items");
        if (items == null) {
            items = new ArrayList<String>();
        }
        if (action != null) {
            if (action.equals("register")) {
                String name = request.getParameter("username");
                session.setAttribute("username", name);
                response.sendRedirect("shoppingList");

            } else if (action.equals("add")) {
                String itemName = request.getParameter("addItem");
                items.add(itemName);
                session.setAttribute("items", items);
                response.sendRedirect("shoppingList");

            } else if (action.equals("delete")) {
                    String sel = (String) request.getParameter("name");
                    System.out.println(sel);
                    for (int i = 0; i < items.size(); i++) 
                        if (sel.equalsIgnoreCase(items.get(i))) {
                            items.remove(i);
                            session.setAttribute("items", items);
                            response.sendRedirect("shoppingList");
                        }
                    }
            }
        }
    }

