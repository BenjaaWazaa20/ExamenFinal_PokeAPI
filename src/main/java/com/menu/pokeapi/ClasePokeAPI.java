package com.menu.pokeapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

public class ClasePokeAPI {
     private Set<Pokemon> listaPokemones = new HashSet<>();

    
    public void MostrarPokemon(JTable tablaPokemon, JTextField buscador,JTextField nombre, JTextField peso, JTextField altura,JTextField experienciaBase, JLabel foto){
     DefaultTableModel modelo = new  DefaultTableModel();
      String[] nombreColumnas  = {"Nombre", "Peso", "Altura"};
      modelo.setColumnIdentifiers(nombreColumnas);
      
      tablaPokemon.setModel(modelo);
     
      try   {
          URL url = new URL("https://pokeapi.co/api/v2/pokemon/"+ buscador.getText());
         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
         conn.setRequestMethod("GET");
         BufferedReader reader = new  BufferedReader(new InputStreamReader(conn.getInputStream()));
         StringBuilder response = new  StringBuilder();
         String line;
         
         while((line = reader.readLine())!=null){
         response.append(line);
         }
         reader.close();
         JSONObject jsonObJECT = new  JSONObject(response.toString());
         String name = jsonObJECT.getString("name");
         int weight = jsonObJECT.getInt("weight");
         int heignt = jsonObJECT.getInt("height");
         int experience = jsonObJECT.getInt("base_experience");
         
         Pokemon p = new Pokemon(name, weight, heignt, experience);
         listaPokemones.add(p);
         System.out.println("Pokemon guardados: " + listaPokemones.size());


         
         
         modelo.setRowCount(0);


        for (Pokemon pO : listaPokemones) {
        modelo.addRow(new Object[]{
        pO.getNombre(),
        pO.getPeso(),
        pO.getAltura()
    });
}

         
         nombre.setText(name);
         peso.setText(String.valueOf(weight));
         altura.setText(String.valueOf(heignt));
         experienciaBase.setText(String.valueOf(experience));
         
         String imageUrl = jsonObJECT.getJSONObject("sprites").getString("front_default");

         ImageIcon icon = new ImageIcon(new URL(imageUrl));
         foto.setIcon(icon);
         
      } catch (Exception e){
          JOptionPane.showMessageDialog(null, "Ingrese valores correctos, solo ID o nombre del Pokemon existente.\nError: " + e.toString());

          
      }
      
        
    }
}
