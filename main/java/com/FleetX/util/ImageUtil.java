package com.FleetX.util;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.http.Part;

public class ImageUtil {

    public String getImageNameFromPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            for (String s : contentDisp.split(";")) {
                if (s.trim().startsWith("filename")) {
                    String fileName = s.substring(s.indexOf('=') + 1).trim().replace("\"", "");
                    if (!fileName.isEmpty()) {
                        return fileName;
                    }
                }
            }
        }
        return "download.png";
    }

    public String uploadImage(Part part, String appPath, String categoryFolder) {
        String imageName = getImageNameFromPart(part);

        // Construct the server-side upload path
        String uploadDirPath = appPath + "assets" + File.separator + "vehicle" + File.separator + categoryFolder;
        File uploadDir = new File(uploadDirPath);

        // Create directory if it doesn't exist
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            System.err.println("Failed to create directory: " + uploadDirPath);
            return null;
        }

        // Full file path
        String fullPath = uploadDirPath + File.separator + imageName;

        try {
            part.write(fullPath);
            // Return relative path to be used in DB
            return categoryFolder + "/" + imageName;
        } catch (IOException e) {
            System.err.println("Image upload failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
