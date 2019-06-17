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
public enum Tipo {
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
    CLASE {
        @Override
        public boolean isNumero() {
            return false;
        }
    
    };
    
    public abstract boolean isNumero();
    
    private String object = null;

    /**
     * @return the objeto
     */
    public String getObject() {
        return object;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObject(String object) {
        this.object = object;
    }
    
    
}
