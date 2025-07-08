package main.service;

import main.entity.Contatto;

import java.util.List;
import java.util.regex.Pattern;

public interface ContattoService {
    public List<Contatto> caricaDaCSV(String file);
    public boolean addContatto(Contatto contatto,String file);
    public List<Contatto> cercaContattoNome(String nome,String file);
    public List<Contatto> cercaContattoPattern(String regex,String file);
    public boolean ControlloId(int id,String file);
}
