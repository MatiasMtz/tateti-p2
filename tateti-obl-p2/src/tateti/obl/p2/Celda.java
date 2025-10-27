/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tateti.obl.p2;

/**
 *
 * @author matim
 */
public class Celda {
    private String valor;
    private String color;
    
    // Constructor para continuar partidas.
    public Celda(String valor, String color) {
        this.valor = valor;
        this.color = color;
    }
    public Celda() {
        this.valor = "";
        this.color = "";
    }    
    
    public String getValor() {
        return this.valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
