package br.calebe.ticketmachine.core;

import java.util.Iterator;

class Troco {

    protected PapelMoeda[] papeisMoeda;

    public Troco(int valor) {
        papeisMoeda = new PapelMoeda[6];
        
        int[] valoresMoeda = {100, 50, 20, 10, 5, 2}; // denominações disponíveis
        for (int i = 0; i <= 5; i++) {
            int quantidade = valor / valoresMoeda[i]; // quantidade de notas/moedas do valor atual
            papeisMoeda[i] = new PapelMoeda(valoresMoeda[i], quantidade);
            valor -= quantidade * valoresMoeda[i]; // subtrai o valor correspondente
        }
    }

    public Iterator<PapelMoeda> getIterator() {
        return new TrocoIterator(this);
    }

    class TrocoIterator implements Iterator<PapelMoeda> {

        protected Troco troco;
        private int currentIndex = 5; // Começa na maior denominação
        private int lastReturnedIndex = -1; // Índice do último elemento retornado

        public TrocoIterator(Troco troco) {
            this.troco = troco;
        }

        @Override
        public boolean hasNext() {
            for (int i = currentIndex; i >= 0; i++) {
                if (troco.papeisMoeda[i] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public PapelMoeda next() {
            for (; currentIndex >= 0; currentIndex--) {
                if (troco.papeisMoeda[currentIndex] != null) {
                    lastReturnedIndex = currentIndex;
                    return troco.papeisMoeda[currentIndex--];
                }
            }
            throw new IllegalStateException("No more elements");
        }

        @Override
        public void remove() {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException("next() has not been called, or remove() was already called");
            }
            troco.papeisMoeda[lastReturnedIndex] = null; 
            lastReturnedIndex = -1;
        }
    }
}
