package br.com.cod3r.exerciciossb.Parser;

import br.com.cod3r.exerciciossb.Model.entities.ChaveObjeto;
import org.json.simple.JSONObject;

public class Parser {
    private ChaveObjeto chave;

    public Parser(ChaveObjeto chave) {
        this.chave = chave;
    }

    @SuppressWarnings("unchecked")
    public JSONObject parser(Object jsonObject){
        JSONObject retorno = new JSONObject();
        retorno.put("ID",chave.getID());
        retorno.put("CLIENTES",chave.getCLIENTES());
        retorno.put("NOME",chave.getNOME());
        retorno.put("DATA_MODIFICACAO",chave.getDATA_MODIFICACAO());
        retorno.put("URA",chave.getURA());
        retorno.put("OBJETO", jsonObject);
        retorno.put("STATUS",chave.getSTATUS());
        return retorno;
    }

    public ChaveObjeto getChave() {
        return chave;
    }

    public void setChave(ChaveObjeto chave) {
        this.chave = chave;
    }
}
