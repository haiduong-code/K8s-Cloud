package com.example.k8scloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class WebController {

    // API 1: Hello World - Nhẹ nhàng
    @GetMapping("/")
    public String hello() throws UnknownHostException {
        // Trả về tên Hostname (Trong K8s chính là tên Pod)
        // Giúp bạn biết request đang được xử lý bởi Pod nào khi load balancing
        return "Hello from K8s! Processed by Pod: " + InetAddress.getLocalHost().getHostName();
    }

    // API 2: Stress CPU - Dùng để test Autoscaling
    @GetMapping("/stress")
    public String stress() {
        double x = 0.0001;
        // Vòng lặp tính toán vô nghĩa để ép CPU lên cao
        for (int i = 0; i <= 1000000; i++) {
            x += Math.sqrt(x);
        }
        return "CPU Stress task finished! Result: " + x;
    }
}