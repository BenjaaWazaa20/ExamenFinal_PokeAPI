package com.menu.pokeapi;

public class Pokemon {

    private String nombre;
    private int peso;
    private int altura;
    private int experiencia;

    public Pokemon(String nombre, int peso, int altura, int experiencia) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
        this.experiencia = experiencia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPeso() {
        return peso;
    }

    public int getAltura() {
        return altura;
    }

    public int getExperiencia() {
        return experiencia;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pokemon)) {
            return false;
        }
        Pokemon p = (Pokemon) obj;
        return nombre.equals(p.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

}
