package com.sinosoft.pdf;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 前端传入后端：
 * 后端返回： pdf地址
 * Created by Carey on 2017/7/10.
 */
@WebServlet(value = "/PdfPreviewServlet")
public class PdfPreviewServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        System.out.println("query");
        req.setCharacterEncoding("utf-8");
        String callbackFunName =req.getParameter("callbackhome");//获取的就是success_jsonpCallback 字符串
        System.out.println("获取callbakfunname");
        String pdf = "http://127.0.0.1/pdfview/file/output_1.pdf";
        resp.getWriter().append(callbackFunName + "([ { pdf:\'"+pdf+"\'}])");
        System.out.println("封装resp");
        resp.setContentType("text/plain");
        resp.getWriter().flush();
        System.out.println("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        super.doGet(req, resp);
    }

    private void splitPdf(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss-SSS");
            System.out.println(sdf.format(new Date()));
            FileInputStream fis = new FileInputStream("d:\\test.pdf");
            int pages = ItextPdfUtil.getTotalPages(fis);
            for (int i = 0; i < pages; i++) {
                String out = "d:\\output"+(i+1)+".pdf";
                ItextPdfUtil.splitPDF(new FileInputStream("d:\\test.pdf"), new FileOutputStream(out), i + 1, i + 1);
            }

            System.out.println(sdf.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
