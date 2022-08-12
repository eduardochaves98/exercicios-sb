package br.com.cod3r.exerciciossb.controllers;

import br.com.cod3r.exerciciossb.Model.entities.ChaveObjeto;
import br.com.cod3r.exerciciossb.Model.repositories.ChaveRepository;
import br.com.cod3r.exerciciossb.Parser.Parser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/service")
public class ChaveController {
    @Autowired
    private ChaveRepository chaveRepository;

    @GetMapping
    public Object consultaChave(@RequestParam String nome) throws ParseException {
        Integer id = null;
        try {
            id = chaveRepository.findByNOMEEquals(nome).getID();
        }catch (Exception e){
            return null;
        }

         ChaveObjeto chave = chaveRepository.findById(id).isPresent()?
                 chaveRepository.findById(id).get()
                 :null;
        if (chave != null) {
            JSONParser jsonParser = new JSONParser();
            String content = chave.getOBJETO();
            Object jsonObject = new Object();
            if (!content.isEmpty()) {
               jsonObject = jsonParser.parse(content);
            }
            Parser parse = new Parser(chave);

            return parse.parser(jsonObject);
        }
        else return null;
    }
    @PostMapping(path = "/chaveObjeto")
    public Object cosultakey(String NOME, String CLIENTES, String URA) throws ParseException {
        ChaveObjeto chave = chaveRepository.findByNOMEEqualsAndCLIENTESEqualsAndURAEquals(NOME, CLIENTES, URA);
        if(chave != null){
            return consultaChave(NOME);
        }else {
            HashMap<String,Object> jsonObject = new HashMap<>();

            jsonObject.put("NOME","");
            jsonObject.put("URA", "");
            jsonObject.put("STATUS", "");
            jsonObject.put("OBJETO", "");

            return jsonObject;
        }
    }

    @PostMapping
    public JSONObject postKey(@Valid ChaveObjeto chave) throws ParseException {
        if(chave.getOBJETO().isEmpty()){
            chave.setOBJETO(null);
        }
        return  consultaChave(chave.getNOME()) != null ? updateChave(chave, chaveRepository.findByNOMEEquals(chave.getNOME()).getID()) : insereChave(chave);
    }

    @PostMapping(path = "/delete")
    public void deleteKey(@RequestParam String NOME) throws ParseException {
        try{
            ChaveObjeto chave = chaveRepository.findByNOMEEquals(NOME);
            if(consultaChave(chave.getNOME()) != null){
                chaveRepository.deleteById(chaveRepository.findByNOMEEquals(chave.getNOME()).getID());
            }
        }catch (Exception ignored){

        }

    }

    private JSONObject updateChave(ChaveObjeto chave, Integer id) throws ParseException {
        chave.setID(id);
        return insereChave(chave);
    }

    public JSONObject insereChave(@Valid ChaveObjeto chave) throws ParseException {

        String obj = chave.getOBJETO()
                .replaceAll("\n","")
                .replaceAll("\t","")
                .replaceAll(" ","");

        chave.setOBJETO(obj);

        chave.setDATA_MODIFICACAO(new Date());

        chaveRepository.save(chave);


        JSONParser jsonParser = new JSONParser();

        Object jsonObject = jsonParser.parse(obj);

        Parser parse = new Parser(chave);

        return parse.parser(jsonObject);
    }
}
