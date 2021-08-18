package com.example.FastCampusWeb05.controller;

import com.example.FastCampusWeb05.component.Calculator;
import com.example.FastCampusWeb05.component.ICalculator;
import com.example.FastCampusWeb05.dto.Req;
import com.example.FastCampusWeb05.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x,@RequestParam int y){
        return calculator.sum(x,y);
    }

    @PostMapping("/minus")
    public Res minus(@RequestBody Req req){
        int result = calculator.minus(req.getX(), req.getY());

        Res res = new Res();
        res.setResult(result);
        res.setResponse(new Res.Body());
        return res;
    }
}
