package com.andlvovsky.wvs.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpResponseFileSender {
  public void sendExcel(HttpServletResponse response, File file, String name) throws IOException {
    response.setContentType("application/vnd.ms-excel");
    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
    try (OutputStream out = response.getOutputStream();
         FileInputStream in = new FileInputStream(file)) {
      IOUtils.copy(in, out);
    } finally {
      Files.delete(file.toPath());
    }
  }
}
