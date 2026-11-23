/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criatale.main;

import javax.swing.JOptionPane;

// Classe de apoio para simular a geração do enredo em memória
class GeradorHistoria {
    public String gerarEnredo(String genero) {
        if (genero != null && genero.equalsIgnoreCase("Fantasia")) {
            return "Em um reino esquecido pelo tempo, um jovem aprendiz descobre um grimório com feitiços perdidos.";
        } else if (genero != null && genero.equalsIgnoreCase("Ficção Científica")) {
            return "No ano de 2150, a primeira missão tripulada a Marte detecta um sinal de rádio artificial vindo do subsolo.";
        } else {
            return "Um mistério intrigante se inicia quando uma carta anônima chega à porta de um detetive.";
        }
    }
}

public class Principal {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bem-vindo ao protótipo do CriaTale!");
        
        // Simula a entrada de dados do usuário para gerar a história
        String generoEscolhido = JOptionPane.showInputDialog(
            null, 
            "Escolha o gênero da história:\n1. Fantasia\n2. Ficção Científica\n3. Suspense", 
            "CriaTale - Geração de Histórias", 
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (generoEscolhido != null && !generoEscolhido.trim().isEmpty()) {
            GeradorHistoria gerador = new GeradorHistoria();
            String historiaGerada = gerador.gerarEnredo(generoEscolhido);
            
            // Exibe o resultado do protótipo de forma independente
            JOptionPane.showMessageDialog(
                null, 
                "=== HISTÓRIA GERADA COM SUCESSO ===\n\n" +
                "Gênero selecionado: " + generoEscolhido + "\n\n" +
                "Enredo:\n" + historiaGerada, 
                "Resultado do Protótipo", 
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário.");
        }
    }
}
