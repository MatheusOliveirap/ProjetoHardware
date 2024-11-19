
package model.DAO;

import Tela.TelaLogin;
import Tela.TelaPrincipal;
import java.sql.*;
import javax.swing.JOptionPane;
import model.DTO.UsuarioDTO;

public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
     public void logar(UsuarioDTO usuDTO) { 
    String sql = "select * from Usuario where nome = ? and senha = ?";
    conexao = Conexao.conector();

    try {
        
        pst = conexao.prepareStatement(sql);
        pst.setString(1, usuDTO.getNome()); 
        pst.setString(2, usuDTO.getSenha());
        rs = pst.executeQuery();

        if (rs.next()) {
            String perfil = rs.getString("perfil"); 
            TelaPrincipal principal = new TelaPrincipal();
            principal.setVisible(true);
            

            conexao.close();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "** tela Login *** " + e.getMessage());
    }
     }
    
    public void NovoUsu(UsuarioDTO objUsuaDTO) {

        String sql = "insert into usuario (nome, senha, perfil) values (?, ?, ?)";

        conexao = Conexao.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objUsuaDTO.getNome());
            pst.setString(2, objUsuaDTO.getSenha());
            pst.setString(3, objUsuaDTO.getPerfil());
            
           pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");

            pst.close();

        } catch (Exception e) {
            if (e.getMessage().contains("'usuario.username'")) {

            }
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public void ApagaUsu(UsuarioDTO objUsuaDTO) {
        String sql = "delete from usuario where id = ?";
        conexao = Conexao.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuaDTO.getId());

            int del = pst.executeUpdate();

            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
          

                conexao.close();

            }

        } catch (Exception e) {

        }
    }

    public void EditarUsu(UsuarioDTO objUsuaDTO) {

        String sql = "update usuario set nome_usuario = ?, senha_usuario = ?, tipo_usuario = ? where id = ?";
        conexao = Conexao.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objUsuaDTO.getNome());
            pst.setString(2, objUsuaDTO.getSenha());
        //    pst.setString(3, objUsuaDTO.getTipo());
            pst.setInt(4, objUsuaDTO.getId());

            int edit = pst.executeUpdate();

            if (edit > 0) {
                
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
           

                conexao.close();

            }

        } catch (Exception e) {

        }

    }

    public void Procura(UsuarioDTO objUsuaDTO) {

        String sql = "select * from usuario where id = ?";
       //Conector = conector.();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuaDTO.getId());
            rs = pst.executeQuery();

            if (rs.next()) {
                objUsuaDTO.setNome(rs.getString("nome_usuario"));
                objUsuaDTO.setSenha(rs.getString("senha_usuario"));
              //  objUsuaDTO.setTipo(rs.getString("tipo_usuario"));

                conexao.close();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario não existe");
              
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Método Pesquisar" + e);
        }
    }
    }





