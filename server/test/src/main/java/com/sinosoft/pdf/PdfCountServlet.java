package com.sinosoft.pdf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * Created by Carey on 2017/7/11.
 */
@WebServlet(value = "/PdfCountServlet")
public class PdfCountServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        System.out.println("count");
        req.setCharacterEncoding("utf-8");
        String callbackFunName =req.getParameter("callbackd");//获取的就是success_jsonpCallback 字符串
        int pages = ItextPdfUtil.getTotalPages(new FileInputStream("d:\\test.pdf"));
        System.out.println("总页数"+pages);

        resp.getWriter().append(callbackFunName + "([ { totalPage:\'"+pages+"\'}])");
        resp.setContentType("text/plain");
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        super.doGet(req, resp);
    }
}
