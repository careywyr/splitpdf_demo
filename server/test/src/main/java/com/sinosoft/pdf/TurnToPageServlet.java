package com.sinosoft.pdf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Carey on 2017/7/13.
 */
@WebServlet(value = "/TurnToPageServlet")
public class TurnToPageServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        req.setCharacterEncoding("utf-8");
        String callbackFunName =req.getParameter("callbackpage");//获取的就是success_jsonpCallback 字符串
        String pdfName = req.getParameter("pdf");
        int totalPage = Integer.valueOf(req.getParameter("totalPage"));
        pdfName = pdfName.substring(0,pdfName.indexOf("."));
        String pageNo = req.getParameter("pageNo");
        if(null==pageNo||"".equals(pageNo)){
            pageNo = "1";
        }
        if(Integer.valueOf(pageNo)>totalPage){
            pageNo = String.valueOf(totalPage);
        }
        if(Integer.valueOf(pageNo)<=0){
            pageNo = "1";
        }
        pdfName = pdfName+"_"+String.valueOf(pageNo)+".pdf";
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
