package com.example.invoiceapp.controller;


import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/api/numbers")
@CrossOrigin(origins = "*")
public class NumberController {

    public static class NumberRequest {
        public List<String> numbers;
        public List<String> getNumbers() { return numbers; }
        public void setNumbers(List<String> numbers) { this.numbers = numbers; }
    }

    @PostMapping
    public Map<String, Object> receiveNumbers(@RequestBody NumberRequest request) {
        if (request.numbers == null) {
            throw new IllegalArgumentException("numbers must not be null");
        }

        List<Map<String, String>> validated = new ArrayList<>();
        for (String s : request.numbers) {
            if (s == null || !s.matches("[+-]?[0-9]+")) {
                throw new IllegalArgumentException("Invalid integer string: " + s);
            }
            BigInteger bi = new BigInteger(s);
            Map<String, String> entry = new HashMap<>();
            entry.put("original", s);
            entry.put("asBigInt", bi.toString());
            validated.add(entry);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("ok", true);
        response.put("count", validated.size());
        response.put("preview", validated.subList(0, Math.min(10, validated.size())));
        return response;
    }
}
