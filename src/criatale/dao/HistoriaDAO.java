/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criatale.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class HistoriaDAO {

    public boolean salvarHistoria(String titulo, String genero, String publicoAlvo, String extensao, String status, String enredo) {
        String sql = "INSERT INTO historias (titulo, genero, publico_alvo, extensao, status_historia, enredo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new ConexaoDAO().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            stmt.setString(1, titulo);
            stmt.setString(2, genero);
            stmt.setString(3, publicoAlvo);
            stmt.setString(4, extensao);
            stmt.setString(5, status);
            stmt.setString(6, enredo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar história: " + e.getMessage());
            return false;
        }
    }

    public List<String[]> listarHistorias() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, genero, status_historia, enredo FROM historias ORDER BY id DESC";
        try (Connection conn = new ConexaoDAO().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) return lista;

            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("titulo"),
                    rs.getString("genero"),
                    rs.getString("status_historia"),
                    rs.getString("enredo")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar histórias: " + e.getMessage());
        }
        return lista;
    }

    public boolean atualizarStatusHistoria(int id, String novoStatus) {
        String sql = "UPDATE historias SET status_historia = ? WHERE id = ?";
        try (Connection conn = new ConexaoDAO().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

    public boolean salvarPersonagem(String nome, String papel, String descricao) {
        String sql = "INSERT INTO personagens (nome, papel, descricao) VALUES (?, ?, ?)";
        try (Connection conn = new ConexaoDAO().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            stmt.setString(1, nome);
            stmt.setString(2, papel);
            stmt.setString(3, descricao);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar personagem: " + e.getMessage());
            return false;
        }
    }

    public List<String[]> listarPersonagens() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT nome, papel, descricao FROM personagens ORDER BY id DESC";
        try (Connection conn = new ConexaoDAO().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) return lista;

            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("nome"),
                    rs.getString("papel"),
                    rs.getString("descricao")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar personagens: " + e.getMessage());
        }
        return lista;
    }
}
