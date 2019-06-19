/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.entorno;

/**
 *
 * @author oscar
 */
public class Tipo {

    public type tipo;
    public String objeto;
    public type subtipo;

    public Tipo(type tipo) {
        this.tipo = tipo;
        this.objeto = null;
        this.subtipo = null;
    }

    public Tipo(type tipo, String objeto) {
        this.tipo = tipo;
        this.objeto = objeto;
    }

    public Tipo(type tipo, type subtipo) {
        this.tipo = tipo;
        this.objeto = null;
        this.subtipo = subtipo;
    }

    public enum type {
        INT {
            @Override
            public boolean isNumero() {
                return true;
            }
        },
        DOUBLE {
            @Override
            public boolean isNumero() {
                return true;
            }

        },
        CHAR {
            @Override
            public boolean isNumero() {
                return false;
            }

        },
        BOOLEAN {
            @Override
            public boolean isNumero() {
                return false;
            }

        },
        STRING {
            @Override
            public boolean isNumero() {
                return false;
            }
        },
        OBJECT {
            @Override
            public boolean isNumero() {
                return false;
            }

        },
        VOID {
            @Override
            public boolean isNumero() {
                return false;
            }

        },
        CLASS {
            @Override
            public boolean isNumero() {
                return false;
            }

        },
        ARRAY {
            @Override
            public boolean isNumero() {
                return false;
            }

        };

        public abstract boolean isNumero();
    }
}
