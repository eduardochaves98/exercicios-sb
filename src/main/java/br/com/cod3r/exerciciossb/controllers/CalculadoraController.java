package br.com.cod3r.exerciciossb.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "calculadora")
public class CalculadoraController {
    @GetMapping(path = "/somar/{n1}/{n2}")
    public int Somar(@PathVariable int n1,
                     @PathVariable int n2){
        return n1 + n2;
    }
    @GetMapping(path = "/subtrair")
    public int Subtrair(@RequestParam(name = "a") int a,
                        @RequestParam(name = "b") int b){
        return a - b;
    }
}
