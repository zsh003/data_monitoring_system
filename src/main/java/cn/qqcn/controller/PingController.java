package cn.qqcn.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class PingController {

    @GetMapping(value = "/api/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> ping(@RequestParam String ip) {
        PingResponse response = new PingResponse();
        Process process;
        try {
            String command = System.getProperty("os.name").toLowerCase().contains("win") ?
                    "ping -n 4 " + ip : "ping -c 4 " + ip;

            process = new ProcessBuilder(command.split(" "))
                    .redirectErrorStream(true)
                    .start();

            // 关键修改：Windows系统使用GBK编码，Linux/Mac使用UTF-8
            Charset charset = System.getProperty("os.name").toLowerCase().contains("win") ?
                    Charset.forName("GBK") : Charset.forName("UTF-8");

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream(), charset));  // 使用正确的编码

            StringBuilder result = new StringBuilder();
            String line;
            int replyCount = 0;
            boolean hasError = false;

            while ((line = reader.readLine()) != null) {
                if (result.length() > 0) result.append("\n");
                result.append(line);

                // 调试输出（可选）
                System.out.println("原始输出: " + line);

                // 中文Windows成功响应：来自 192.168.102.1 的回复: 字节=32
                // 英文Windows成功响应：Reply from 192.168.102.1: bytes=32
                if (line.contains(ip) &&
                        (line.contains("字节=") ||
                                line.contains("bytes=") ||
                                line.contains("回复"))) {
                    replyCount++;
                }

                // 错误检测（中英文）
                if (line.contains("无法访问目标主机") ||
                        line.contains("Destination host unreachable") ||
                        line.contains("请求超时") ||
                        line.contains("Request timed out")) {
                    hasError = true;
                }
            }

            int exitCode = process.waitFor();

            // 最终判断：收到至少1个有效回复且没有错误
            response.setSuccess(replyCount > 0 && !hasError);
            response.setPingResults(result.toString());

        } catch (IOException | InterruptedException e) {
            response.setSuccess(false);
            response.setPingResults("Error: " + e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(response), headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("{\"success\":false,\"pingResults\":\"Error in processing response\"}",
                    headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class PingResponse {
        private boolean success;
        private String pingResults;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getPingResults() {
            return pingResults;
        }

        public void setPingResults(String pingResults) {
            this.pingResults = pingResults;
        }
    }
}