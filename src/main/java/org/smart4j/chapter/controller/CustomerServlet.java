package org.smart4j.chapter.controller;

import org.smart4j.chapter.model.Customer;
import org.smart4j.chapter.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 客户列表页面
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keywords = req.getParameter("keywords");
        List<Customer> customerList = customerService.getCustomerList(keywords);
        req.setAttribute("customList", customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO
    }
}
