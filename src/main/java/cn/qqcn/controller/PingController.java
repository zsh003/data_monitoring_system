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
            // 根据操作系统调整ping命令和参数
            String command = System.getProperty("os.name").toLowerCase().contains("win") ?
                    "ping -n 4 " + ip : "ping -c 4 " + ip;

            process = new ProcessBuilder(command.split(" "))
                    .redirectErrorStream(true)
                    .start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (result.length() > 0) result.append("\n");
                result.append(line);
            }

            int exitCode = process.waitFor();
            response.setSuccess(exitCode == 0);
            response.setPingResults(result.toString());
        } catch (IOException | InterruptedException e) {
            response.setSuccess(false);
            response.setPingResults("Error: " + e.getMessage());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(response), headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("{\"success\":false,\"pingResults\":\"Error in processing response\"}", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class PingResponse {
        private boolean success;
        private String pingResults;

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getPingResults() {
            return pingResults;
        }

        public void setPingResults(String pingResults) {
            this.pingResults = pingResults;
        }
    }
}