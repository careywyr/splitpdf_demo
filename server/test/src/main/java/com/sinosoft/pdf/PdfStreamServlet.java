package com.sinosoft.pdf;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Carey on 2017/7/11.
 */
@WebServlet(value = "/PdfStreamServlet")
public class PdfStreamServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
        throws ServletException, IOException
    {
        System.out.println("stream");
        try
        {
            req.setCharacterEncoding("utf-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setContentType("application/pdf;charset=UTF-8");
            URL url = new URL("http://localhost:8080/pdfview/file/output.pdf");
            URLConnection conn = url.openConnection();
            InputStream input = new BufferedInputStream(conn.getInputStream());
            byte buffBytes[] = new byte[1024];
            ServletOutputStream out = response.getOutputStream();
            int read = 0;
            while ((read = input.read(buffBytes)) != -1) {
                out.write(buffBytes, 0, read);
            }
            out.flush();
            out.close();

//            String pdf = "http://localhost/pdfview/file/output.pdf";
//            JSONObject result = new JSONObject();
//            result.put("pdf", pdf);
//            response.getWriter().append(
//                JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect));
//            response.setContentType("application/json");
//            response.getWriter().flush();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        super.doGet(req, resp);
    }
}
