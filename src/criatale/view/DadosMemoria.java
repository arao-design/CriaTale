/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criatale.view;

import java.util.ArrayList;
import java.util.List;

public class DadosMemoria {
    public static String usuarioLogado = "Escritor";
    public static List<String[]> historias = new ArrayList<>();
    public static List<String[]> personagens = new ArrayList<>();

    static {
        // Dados iniciais para preencher os wireframes logo ao abrir
        historias.add(new String[]{"A Espada de Cristal", "Fantasia", "Concluída", "Um jovem ferreiro encontra uma lâmina antiga nas ruínas."});
        historias.add(new String[]{"Sinal de Órion", "Ficção Científica", "Em Andamento", "Uma estação espacial recebe uma transmissão do futuro."});
        
        personagens.add(new String[]{"Kaelen", "Protagonista", "Ferreiro curioso com marca arcana no braço."});
        personagens.add(new String[]{"Dra. Elena", "Cientista", "Especialista em astrofísica e sinais de rádio."});
    }
}
