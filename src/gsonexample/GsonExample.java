/*
 * Copyright (C) 2017 haynes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gsonexample;

import gsonexample.cliente.Cliente;
import gsonexample.pedido.Pedido;
import gsonexample.pedido.itens.ItemPedido;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author haynes
 */
public class GsonExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * Como escrever um arquivo JSON
         */
        createJSON();
        
        System.out.println("\n\n");
        
        /**
         * Como ler arquivo json
         */
        try {
            File filePath = new File("dist/pedido.json");
            loadJSON(filePath);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GsonExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createJSON() {
        Pedido pedido = new Pedido();

        Cliente cliente = new Cliente();
        cliente.setId(12);
        cliente.setNome("Guilherme Haynes");

        pedido.setId(1234);
        pedido.setCliente(cliente);

        for (int i = 1; i <= 3; i++) {
            ItemPedido item = new ItemPedido();
            item.setId(i);
            item.setDescricao("Item " + i);
            item.setQtd(10);
            pedido.getItens().add(item);
        }

        Gson gson = new Gson();
        String json = gson.toJson(pedido);

        System.out.println(json);
    }

    public static void loadJSON(File filePath) throws FileNotFoundException {
        Reader reader = new FileReader(filePath);

        Gson gson = new Gson();
        Pedido pedido = gson.fromJson(reader, Pedido.class);

        System.out.println("Pedido nº: " + pedido.getId());
        System.out.println("Cliente nº: " + pedido.getCliente().getId() + " | Nome: " + pedido.getCliente().getNome());
        System.out.println("Itens: ");
        for (ItemPedido item : pedido.getItens()) {
            System.out.println("----------------------------------------------------------");
            System.out.println("   Ped. nº: " + item.getId());
            System.out.println("   Item: " + item.getDescricao());
            System.out.println("   Qtd: " + item.getQtd());
        }

    }

}
