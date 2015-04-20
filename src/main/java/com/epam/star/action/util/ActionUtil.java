package com.epam.star.action.util;

import com.epam.star.action.ActionException;
import com.epam.star.entity.Image;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

public class ActionUtil {

    public static Image getImageFromRequestPart(HttpServletRequest request, String s) throws ActionException {
        try {
            Part part = request.getPart(s);

            if (part != null) {
                String filename = getFilename(part);
                if (filename.equals("")) {
                    return null;
                } else {
                    int size = (int) part.getSize();
                    InputStream inputStream = part.getInputStream();
                    byte[] bytes = new byte[size];
                    inputStream.read(bytes);
                    return new Image(filename, bytes);
                }
            }
        } catch (IOException | ServletException e) {
            throw new ActionException(e);
        }
        return null;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
