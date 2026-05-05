package com.calculator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class AdditionServlet
 * This servlet handles the addition of two numbers provided via an HTML form.
 */
@WebServlet("/AdditionServlet")
public class AdditionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles POST requests from the calculator form.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the response content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Retrieve parameters from the request
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");
        
        // Start generating the HTML response
        out.println("<html><head><title>Addition Result</title>");
        out.println("<style>body { font-family: Arial, sans-serif; margin: 50px; } .error { color: red; }</style>");
        out.println("</head><body>");
        
        try {
            // Input Validation: Check if inputs are null or empty
            if (num1Str == null || num2Str == null || num1Str.trim().isEmpty() || num2Str.trim().isEmpty()) {
                throw new Exception("Input fields cannot be empty.");
            }
            
            // Parsing: Convert string inputs to integers
            // Wrapped in try-catch to handle NumberFormatException
            int num1 = Integer.parseInt(num1Str);
            int num2 = Integer.parseInt(num2Str);
            
            // Logic: Perform addition
            int sum = num1 + num2;
            
            // Output: Display the full expression as required
            out.println("<h2>Calculation Result</h2>");
            out.println("<p style='font-size: 1.2em;'>" + num1 + " + " + num2 + " = <strong>" + sum + "</strong></p>");
            
        } catch (NumberFormatException e) {
            // Error Handling: Handle non-numeric inputs
            out.println("<h2 class='error'>Error</h2>");
            out.println("<p>Invalid input! Please enter valid integers (e.g., 12, 35).</p>");
        } catch (Exception e) {
            // Error Handling: Handle empty inputs or other general errors
            out.println("<h2 class='error'>Error</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
        }
        
        // Navigation: Link to go back and calculate again
        out.println("<br><a href='index.html'>Go Back and Calculate Again</a>");
        out.println("</body></html>");
    }

    /**
     * Handles GET requests by delegating to doPost.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
