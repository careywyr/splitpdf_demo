package com.sinosoft.pdf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Carey on 2017/7/11.
 */
@WebServlet(value = "/PdfPrevServlet")
public class PdfPrevServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        System.out.println("prev");
        req.setCharacterEncoding("utf-8");
        String callbackFunName =req.getParameter("callbackprev");//获取的就是success_jsonpCallback 字符串
        String pdfName = req.getParameter("pdf");
        pdfName = pdfName.substring(0,pdfName.indexOf("."));
        String pageNo = req.getParameter("pageNo");
        if(null==pageNo||"".equals(pageNo)){
            pageNo = "0";
        }
        int nextPageNo = 1;
        if(Integer.valueOf(pageNo)>1){
            nextPageNo = Integer.valueOf(pageNo)-1;
        }
        pdfName = pdfName+"_"+String.valueOf(nextPageNo)+".pdf";
        String url = "http://127.0.0.1/pdfview/file/";
        pdfName = url+pdfName;
        resp.getWriter().append(callbackFunName + "([ { pdf:\'"+pdfName+"\'}])");
        resp.setContentType("text/plain");
        resp.getWriter().flush();
        System.out.println(pdfName);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        super.doGet(req, resp);
    }
}
