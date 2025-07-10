package main.service;

import main.entity.Contatto;

import java.util.List;

public interface ContattoService {
    public boolean addContatto(Contatto contatto,String file);
    public List<Contatto> cercaContattoNome(String nome,String file);
    public List<Contatto> cercaContattoPattern(String regex,String file);
    public boolean ControlloId(int id,String file);
    public boolean ControlloRegex(String regex,String campo);
}
